package com.manas.mergegate.integration;

import com.manas.mergegate.domain.BranchRepository;
import com.manas.mergegate.domain.HoldoutResultRepository;
import com.manas.mergegate.domain.PrUnitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * End-to-end wiring test: the real Spring context boots, {@code seed.CorpusDataLoader} loads
 * the real, committed output of the offline corpus generation + PIT run + gate ingestion
 * pipeline (branch_manifest.json, gate_decisions.json, holdout_manifest.json,
 * holdout_results.json under src/main/resources/corpus), and the REST API serves it. This
 * is the fast, always-on confirmation that the wiring is correct; the actual PIT mutation
 * run and holdout evaluation that produced those committed JSON files were run offline (see
 * the README's "Building and running" section) and are not repeated by this test suite,
 * since a full PIT run takes tens of seconds and only needs to happen when the corpus or the
 * gate's decision logic changes.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MergeGateApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private PrUnitRepository prUnitRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private HoldoutResultRepository holdoutResultRepository;

    @Test
    void loaderPopulatesTheRealCommittedCorpusAndGateDecisions() {
        assertThat(prUnitRepository.count()).isEqualTo(245);
        assertThat(branchRepository.count()).isEqualTo(606);
        assertThat(holdoutResultRepository.count()).isEqualTo(60);
    }

    @Test
    void summaryEndpointReflectsTheRealGateRun() {
        Map<?, ?> body = rest.getForObject(url("/api/summary"), Map.class);
        assertThat(body.get("totalPrs")).isEqualTo(245);
        assertThat(body.get("totalBranches")).isEqualTo(606);
        assertThat(((Number) body.get("flaggedBranches")).intValue()).isGreaterThan(0);
    }

    @Test
    void holdoutEndpointReportsZeroFalseBlocksOnTheRealHandLabeledSample() {
        Map<?, ?> body = rest.getForObject(url("/api/holdout"), Map.class);
        assertThat(body.get("total")).isEqualTo(60);
        assertThat(body.get("falseBlocks")).isEqualTo(0);
    }

    @Test
    void prDetailEndpointReturns404ForAnUnknownPr() {
        var response = rest.getForEntity(url("/api/prs/pr9999"), String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }
}
