package com.manas.mergegate.gate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Compares the real gate's per-PR block decisions (gate_decisions.json,
 * produced by GateReportIngestorMain from a real PIT run) against the
 * hand-labeled holdout ground truth (holdout_manifest.json) and reports
 * false blocks (gate blocked a PR hand-labeled as fine) and missed blocks
 * (gate did not block a PR hand-labeled as should-be-blocked), honestly,
 * with no threshold tuning.
 *
 * Usage:
 *   java -cp ... com.manas.mergegate.gate.HoldoutEvaluatorMain
 *        <gate_decisions.json> <holdout_manifest.json> <output holdout_results.json>
 */
public final class HoldoutEvaluatorMain {

    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.err.println("usage: HoldoutEvaluatorMain <gate_decisions.json> <holdout_manifest.json> <output holdout_results.json>");
            System.exit(2);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        JsonNode gateDecisions = mapper.readTree(new File(args[0]));
        JsonNode holdout = mapper.readTree(new File(args[1]));
        JsonNode prBlocked = gateDecisions.get("prBlocked");

        List<Map<String, Object>> rows = new ArrayList<>();
        int falseBlocks = 0;
        int missedBlocks = 0;
        int total = 0;

        for (JsonNode entry : holdout) {
            String prId = entry.get("prId").asText();
            boolean expectedBlock = entry.get("expectedBlock").asBoolean();
            boolean actualBlock = prBlocked.has(prId) && prBlocked.get(prId).asBoolean();
            boolean falseBlock = actualBlock && !expectedBlock;
            boolean missedBlock = expectedBlock && !actualBlock;
            if (falseBlock) {
                falseBlocks++;
            }
            if (missedBlock) {
                missedBlocks++;
            }
            total++;

            var row = new java.util.LinkedHashMap<String, Object>();
            row.put("prId", prId);
            row.put("source", entry.has("source") ? entry.get("source").asText() : "");
            row.put("labelMethod", entry.has("labelMethod") ? entry.get("labelMethod").asText() : "");
            row.put("expectedBlock", expectedBlock);
            row.put("actualBlock", actualBlock);
            row.put("falseBlock", falseBlock);
            row.put("missedBlock", missedBlock);
            rows.add(row);
        }

        var output = new java.util.LinkedHashMap<String, Object>();
        output.put("total", total);
        output.put("falseBlocks", falseBlocks);
        output.put("missedBlocks", missedBlocks);
        output.put("rows", rows);

        mapper.writeValue(new File(args[2]), output);
        System.out.println("holdout total=" + total + " falseBlocks=" + falseBlocks + " missedBlocks=" + missedBlocks);
    }
}
