package com.manas.mergegate.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Batch tool, not a Spring bean: reads the committed security_corpus.json, runs the real
 * TaintAnalyzer/AuthorizationDiffChecker/DependencyProvenanceChecker over it through SecurityGate,
 * and writes security_gate_results.json, mirroring runbook.RunbookCoverageMain.
 *
 * Usage:
 *   java -cp ... com.manas.mergegate.security.SecurityGateMain
 *        <security_corpus.json> <output security_gate_results.json>
 */
public final class SecurityGateMain {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("usage: SecurityGateMain <security_corpus.json> <output security_gate_results.json>");
            System.exit(2);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<SecurityCase> cases = mapper.readValue(new File(args[0]),
                mapper.getTypeFactory().constructCollectionType(List.class, SecurityCase.class));

        SecurityGate gate = new SecurityGate();
        List<SecurityCaseResult> results = gate.evaluateAll(cases);

        Map<String, Object> output = new LinkedHashMap<>();
        output.put("total", results.size());
        output.put("falsePositives", results.stream().filter(SecurityCaseResult::falsePositive).count());
        output.put("missed", results.stream().filter(SecurityCaseResult::missed).count());

        for (String rule : List.of("taint", "authz", "provenance")) {
            List<SecurityCaseResult> ruleResults = results.stream()
                    .filter(r -> r.ruleType().equals(rule)).collect(Collectors.toList());
            Map<String, Object> ruleSummary = new LinkedHashMap<>();
            ruleSummary.put("total", ruleResults.size());
            ruleSummary.put("falsePositives", ruleResults.stream().filter(SecurityCaseResult::falsePositive).count());
            ruleSummary.put("missed", ruleResults.stream().filter(SecurityCaseResult::missed).count());
            output.put(rule, ruleSummary);
        }
        output.put("rows", results);

        mapper.writeValue(new File(args[1]), output);

        System.out.println("total=" + results.size()
                + " falsePositives=" + output.get("falsePositives")
                + " missed=" + output.get("missed"));
    }
}
