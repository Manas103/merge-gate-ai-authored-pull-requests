package com.manas.mergegate.integration;

import com.manas.mergegate.domain.AlertCoverageRepository;
import com.manas.mergegate.domain.MutationScoreResultRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * End-to-end wiring test for the release-gate extension: the real Spring
 * context boots, seed.CorpusDataLoader loads the real, committed output of
 * runbook.RunbookCoverageMain (runbook_coverage.json) and
 * gate.MutationScoreGateMain (mutation_score_results.json, produced from a
 * real PIT run over the 9 seeded zero-kill PR units), and the REST API
 * serves both. This is independent of the original 245-PR-unit corpus and
 * its own MergeGateApiIntegrationTest, which is untouched by this class.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReleaseGateApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private AlertCoverageRepository alertCoverageRepository;
    @Autowired
    private MutationScoreResultRepository mutationScoreResultRepository;

    @Test
    void loaderPopulatesTheRealFortyTwoAlertCatalogWithEighteenGaps() {
        assertThat(alertCoverageRepository.count()).isEqualTo(42);
        long undocumented = alertCoverageRepository.findAll().stream().filter(a -> !a.isDocumented()).count();
        assertThat(undocumented).isEqualTo(18);
    }

    @Test
    void loaderPopulatesTheNineSeededZeroKillMutationScoreResults() {
        assertThat(mutationScoreResultRepository.count()).isEqualTo(9);
        boolean allZeroKill = mutationScoreResultRepository.findAll().stream().allMatch(u -> u.isZeroKill() && u.isBelowFloor());
        assertThat(allZeroKill).isTrue();
    }

    @Test
    void runbookCoverageEndpointNamesTheEighteenAlertsMissingAStep() {
        Map<?, ?> body = rest.getForObject(url("/api/runbook-coverage"), Map.class);
        assertThat(body.get("totalAlerts")).isEqualTo(42);
        assertThat(body.get("undocumentedAlerts")).isEqualTo(18);
        List<?> gaps = (List<?>) body.get("gaps");
        assertThat(gaps).hasSize(18);
        Map<?, ?> firstGap = (Map<?, ?>) gaps.get(0);
        assertThat(firstGap.get("alertId")).isNotNull();
        assertThat(firstGap.get("documented")).isEqualTo(false);
    }

    @Test
    void mutationScoresEndpointReportsAllNineZeroKillUnitsBelowFloor() {
        Map<?, ?> body = rest.getForObject(url("/api/mutation-scores"), Map.class);
        assertThat(body.get("totalUnits")).isEqualTo(9);
        assertThat(((Number) body.get("belowFloorCount")).intValue()).isEqualTo(9);
        assertThat(((Number) body.get("zeroKillCount")).intValue()).isEqualTo(9);
    }

    private String url(String path) {
        return "http://localhost:" + port + path;
    }
}
