package com.manas.mergegate.gate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Batch tool, not a Spring bean: reads the branch registry the corpus
 * generator wrote and the mutations.xml a real `mvn org.pitest:pitest-maven:mutationCoverage`
 * run produced, runs the real GateEngine over them, and writes
 * gate_decisions.json, a real artifact of a real run, not a fabricated one.
 *
 * Usage:
 *   java -cp target/classes;... com.manas.mergegate.gate.GateReportIngestorMain
 *        <branch_manifest.json> <mutations.xml> <gate_decisions.json output>
 */
public final class GateReportIngestorMain {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("usage: GateReportIngestorMain <branch_manifest.json> <mutations.xml> <output gate_decisions.json>");
            System.exit(2);
        }
        File branchManifestFile = new File(args[0]);
        File mutationsXmlFile = new File(args[1]);
        File outFile = new File(args[2]);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<BranchDef> branches = mapper.readValue(branchManifestFile, mapper.getTypeFactory()
                .constructCollectionType(List.class, BranchDef.class));

        PitReportParser parser = new PitReportParser();
        List<Mutation> mutations = parser.parse(mutationsXmlFile);

        GateEngine engine = new GateEngine();
        List<BranchGateResult> results = engine.evaluate(branches, mutations);

        Map<String, Boolean> prBlocked = new LinkedHashMap<>();
        for (BranchDef def : branches) {
            prBlocked.putIfAbsent(def.prId(), false);
        }
        for (BranchGateResult r : results) {
            if (r.flagged()) {
                prBlocked.put(r.prId(), true);
            }
        }

        Map<String, Object> output = new LinkedHashMap<>();
        output.put("branches", results);
        output.put("prBlocked", prBlocked);
        output.put("totalBranches", results.size());
        output.put("flaggedBranches", results.stream().filter(BranchGateResult::flagged).count());
        output.put("coveredBranches", results.stream().filter(BranchGateResult::covered).count());
        output.put("totalPrs", prBlocked.size());
        output.put("blockedPrs", prBlocked.values().stream().filter(Boolean::booleanValue).count());
        output.put("totalMutants", mutations.size());

        mapper.writeValue(outFile, output);

        System.out.println("branches=" + results.size()
                + " flagged=" + output.get("flaggedBranches")
                + " covered=" + output.get("coveredBranches")
                + " prs=" + prBlocked.size()
                + " blockedPrs=" + output.get("blockedPrs")
                + " mutants=" + mutations.size());
    }
}
