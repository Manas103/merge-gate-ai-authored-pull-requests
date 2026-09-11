package com.manas.mergegate.gate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Batch tool, not a Spring bean: reads a branch manifest (the same BranchDef
 * schema branch_manifest.json uses) and a real mutations.xml, runs the real
 * GateEngine to get per-branch results, rolls them up with MutationScoreGate,
 * and writes mutation_score_results.json. Used against zero_kill_manifest.json
 * (the 9 seeded zero-kill PR units, evaluated over the same real PIT run as
 * the main corpus) to produce the committed evidence for the mutation-score
 * floor and zero-kill-suite claims.
 *
 * Usage:
 *   java -cp target/classes;... com.manas.mergegate.gate.MutationScoreGateMain
 *        <branch_manifest.json> <mutations.xml> <output mutation_score_results.json>
 */
public final class MutationScoreGateMain {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("usage: MutationScoreGateMain <branch_manifest.json> <mutations.xml> <output mutation_score_results.json>");
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
        List<BranchGateResult> branchResults = engine.evaluate(branches, mutations);

        MutationScoreGate gate = new MutationScoreGate();
        List<PrMutationScore> scores = gate.scoreAll(branchResults);

        long belowFloor = scores.stream().filter(PrMutationScore::belowFloor).count();
        long zeroKill = scores.stream().filter(PrMutationScore::zeroKill).count();

        Map<String, Object> output = new LinkedHashMap<>();
        output.put("floorScore", PrMutationScore.FLOOR_SCORE);
        output.put("totalUnits", scores.size());
        output.put("belowFloorCount", belowFloor);
        output.put("zeroKillCount", zeroKill);
        output.put("units", scores);

        mapper.writeValue(outFile, output);

        System.out.println("units=" + scores.size()
                + " belowFloor=" + belowFloor
                + " zeroKill=" + zeroKill);
    }
}
