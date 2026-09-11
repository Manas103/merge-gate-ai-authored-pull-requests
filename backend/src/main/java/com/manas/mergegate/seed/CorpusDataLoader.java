package com.manas.mergegate.seed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manas.mergegate.domain.AlertCoverage;
import com.manas.mergegate.domain.AlertCoverageRepository;
import com.manas.mergegate.domain.Branch;
import com.manas.mergegate.domain.BranchRepository;
import com.manas.mergegate.domain.HoldoutResult;
import com.manas.mergegate.domain.HoldoutResultRepository;
import com.manas.mergegate.domain.MutationScoreResult;
import com.manas.mergegate.domain.MutationScoreResultRepository;
import com.manas.mergegate.domain.PrUnit;
import com.manas.mergegate.domain.PrUnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Iterator;

/**
 * Loads the real, committed output of the offline corpus generation + PIT
 * mutation testing + gate ingestion pipeline (see scripts/ and
 * docs/README for the exact commands that produced these files) into the
 * database on startup. This is a read path over real precomputed results,
 * not a live recomputation on every boot; recomputing against a full PIT
 * run on every application start would make the API slow to boot for no
 * benefit, since the corpus and mutation results only change when the
 * pipeline is rerun.
 */
@Component
public class CorpusDataLoader implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(CorpusDataLoader.class);

    private final PrUnitRepository prUnitRepository;
    private final BranchRepository branchRepository;
    private final HoldoutResultRepository holdoutResultRepository;
    private final AlertCoverageRepository alertCoverageRepository;
    private final MutationScoreResultRepository mutationScoreResultRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public CorpusDataLoader(PrUnitRepository prUnitRepository, BranchRepository branchRepository,
                             HoldoutResultRepository holdoutResultRepository,
                             AlertCoverageRepository alertCoverageRepository,
                             MutationScoreResultRepository mutationScoreResultRepository) {
        this.prUnitRepository = prUnitRepository;
        this.branchRepository = branchRepository;
        this.holdoutResultRepository = holdoutResultRepository;
        this.alertCoverageRepository = alertCoverageRepository;
        this.mutationScoreResultRepository = mutationScoreResultRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (prUnitRepository.count() > 0) {
            log.info("corpus already loaded, skipping seed");
            return;
        }
        loadPrUnits();
        loadBranchesAndGateDecisions();
        loadHoldout();
        loadAlertCoverage();
        loadMutationScoreResults();
        log.info("seed complete: {} PR units, {} branches, {} holdout rows, {} alerts, {} mutation-score units",
                prUnitRepository.count(), branchRepository.count(), holdoutResultRepository.count(),
                alertCoverageRepository.count(), mutationScoreResultRepository.count());
    }

    private void loadPrUnits() throws Exception {
        try (InputStream in = new ClassPathResource("corpus/corpus_manifest.json").getInputStream()) {
            JsonNode arr = mapper.readTree(in);
            for (JsonNode n : arr) {
                prUnitRepository.save(new PrUnit(
                        n.get("prId").asText(), n.get("className").asText(),
                        n.get("packageName").asText(), n.get("source").asText(),
                        n.get("description").asText()));
            }
        }
    }

    private void loadBranchesAndGateDecisions() throws Exception {
        try (InputStream in = new ClassPathResource("corpus/gate_decisions.json").getInputStream()) {
            JsonNode root = mapper.readTree(in);
            JsonNode branches = root.get("branches");
            for (JsonNode n : branches) {
                String branchId = n.get("branchId").asText();
                String prId = n.get("prId").asText();
                String fqcn = n.get("fqcn").asText();
                String className = fqcn.substring(fqcn.lastIndexOf('.') + 1);
                branchRepository.save(new Branch(
                        branchId, prId, className, fqcn, n.get("lineNumber").asInt(),
                        n.get("reason").asText(), "SEE_MANIFEST",
                        n.get("covered").asBoolean(), n.get("flagged").asBoolean(),
                        n.get("mutantCount").asInt(), n.get("killedCount").asInt(),
                        n.get("survivedCount").asInt(), n.get("noCoverageCount").asInt(),
                        n.get("reason").asText()));
            }
        }
    }

    private void loadHoldout() throws Exception {
        ClassPathResource resource = new ClassPathResource("corpus/holdout_results.json");
        if (!resource.exists()) {
            log.warn("no holdout_results.json found, skipping holdout seed");
            return;
        }
        try (InputStream in = resource.getInputStream()) {
            JsonNode root = mapper.readTree(in);
            JsonNode rows = root.get("rows");
            Iterator<JsonNode> it = rows.elements();
            while (it.hasNext()) {
                JsonNode n = it.next();
                holdoutResultRepository.save(new HoldoutResult(
                        n.get("prId").asText(), n.get("source").asText(), n.get("labelMethod").asText(),
                        n.get("expectedBlock").asBoolean(), n.get("actualBlock").asBoolean(),
                        n.get("falseBlock").asBoolean(), n.get("missedBlock").asBoolean()));
            }
        }
    }

    /**
     * Loads the real, committed output of runbook.RunbookCoverageMain: the alert catalog
     * intersected with the runbook step registry. Independent of the PR/mutation corpus above;
     * this is the release gate's runbook-coverage rule (see README's "What's new" section).
     */
    private void loadAlertCoverage() throws Exception {
        ClassPathResource resource = new ClassPathResource("runbook/runbook_coverage.json");
        if (!resource.exists()) {
            log.warn("no runbook_coverage.json found, skipping alert coverage seed");
            return;
        }
        try (InputStream in = resource.getInputStream()) {
            JsonNode root = mapper.readTree(in);
            for (JsonNode n : root.get("alerts")) {
                alertCoverageRepository.save(new AlertCoverage(
                        n.get("alertId").asText(), n.get("service").asText(), n.get("severity").asText(),
                        n.get("description").asText(), n.get("documented").asBoolean(), n.get("reason").asText()));
            }
        }
    }

    /**
     * Loads the real, committed output of gate.MutationScoreGateMain run against
     * zero_kill_manifest.json (the 9 seeded zero-kill PR units) and a real
     * mutations.xml. This is the release gate's mutation-score-floor rule.
     */
    private void loadMutationScoreResults() throws Exception {
        ClassPathResource resource = new ClassPathResource("corpus/mutation_score_results.json");
        if (!resource.exists()) {
            log.warn("no mutation_score_results.json found, skipping mutation score seed");
            return;
        }
        try (InputStream in = resource.getInputStream()) {
            JsonNode root = mapper.readTree(in);
            for (JsonNode n : root.get("units")) {
                mutationScoreResultRepository.save(new MutationScoreResult(
                        n.get("prId").asText(), n.get("killedCount").asInt(), n.get("survivedCount").asInt(),
                        n.get("noCoverageCount").asInt(), n.get("hasScoreableMutants").asBoolean(),
                        n.get("score").asDouble(), n.get("belowFloor").asBoolean(),
                        n.get("zeroKill").asBoolean(), n.get("reason").asText()));
            }
        }
    }
}
