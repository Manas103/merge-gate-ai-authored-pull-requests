package com.manas.mergegate.runbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Batch tool, not a Spring bean: reads the committed alert catalog and
 * runbook step registry, runs the real RunbookCoverageChecker over them, and
 * writes runbook_coverage.json, a real artifact of a real run, mirroring how
 * gate.GateReportIngestorMain turns a real PIT run into gate_decisions.json.
 *
 * Usage:
 *   java -cp target/classes;... com.manas.mergegate.runbook.RunbookCoverageMain
 *        <alert_catalog.json> <runbook_steps.json> <output runbook_coverage.json>
 */
public final class RunbookCoverageMain {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("usage: RunbookCoverageMain <alert_catalog.json> <runbook_steps.json> <output runbook_coverage.json>");
            System.exit(2);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<AlertDef> alerts = mapper.readValue(new File(args[0]),
                mapper.getTypeFactory().constructCollectionType(List.class, AlertDef.class));
        List<RunbookStep> steps = mapper.readValue(new File(args[1]),
                mapper.getTypeFactory().constructCollectionType(List.class, RunbookStep.class));

        RunbookCoverageChecker checker = new RunbookCoverageChecker();
        List<RunbookCoverageResult> results = checker.evaluate(alerts, steps);

        long documented = results.stream().filter(RunbookCoverageResult::documented).count();
        long undocumented = results.size() - documented;

        Map<String, Object> output = new LinkedHashMap<>();
        output.put("totalAlerts", results.size());
        output.put("documentedAlerts", documented);
        output.put("undocumentedAlerts", undocumented);
        output.put("alerts", results);

        mapper.writeValue(new File(args[2]), output);

        System.out.println("alerts=" + results.size()
                + " documented=" + documented
                + " undocumented=" + undocumented);
    }
}
