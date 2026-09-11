package com.manas.mergegate.runbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Loads the real, committed 42-alert catalog and runbook step registry
 * (scripts/generate_runbook_catalog.py's committed output, not a hand-rolled
 * fixture) and asserts RunbookCoverageChecker flags exactly the 18 seeded
 * gaps and none of the other 24. The expected id set below was read off the
 * generator's own printed output at generation time, not guessed or tuned
 * after the fact.
 */
class RunbookCoverageCheckerTest {

    private static final Set<String> EXPECTED_MISSING = Set.of(
            "ALERT-001", "ALERT-002", "ALERT-003", "ALERT-006", "ALERT-007", "ALERT-008",
            "ALERT-009", "ALERT-015", "ALERT-016", "ALERT-017", "ALERT-018", "ALERT-020",
            "ALERT-028", "ALERT-031", "ALERT-032", "ALERT-035", "ALERT-041", "ALERT-042");

    @Test
    void flagsExactlyTheEighteenSeededGapsAcrossTheFortyTwoAlertCatalog() throws Exception {
        List<AlertDef> alerts = readCatalog();
        List<RunbookStep> steps = readSteps();
        assertThat(alerts).hasSize(42);

        List<RunbookCoverageResult> flagged = new RunbookCoverageChecker().flagged(alerts, steps);
        assertThat(flagged).hasSize(18);

        Set<String> flaggedIds = flagged.stream().map(RunbookCoverageResult::alertId).collect(Collectors.toSet());
        assertThat(flaggedIds).isEqualTo(EXPECTED_MISSING);

        for (RunbookCoverageResult r : flagged) {
            assertThat(r.documented()).isFalse();
            assertThat(r.reason()).contains("no documented runbook step");
        }
    }

    @Test
    void theOtherTwentyFourAlertsAreDocumented() throws Exception {
        List<AlertDef> alerts = readCatalog();
        List<RunbookStep> steps = readSteps();

        List<RunbookCoverageResult> results = new RunbookCoverageChecker().evaluate(alerts, steps);
        List<RunbookCoverageResult> documented = results.stream().filter(RunbookCoverageResult::documented).toList();

        assertThat(documented).hasSize(24);
        for (RunbookCoverageResult r : documented) {
            assertThat(EXPECTED_MISSING).doesNotContain(r.alertId());
            assertThat(r.reason()).contains("documented runbook step present");
        }
    }

    @Test
    void anAlertWithNoMatchingStepIsFlaggedAndOneWithAMatchIsNot() {
        AlertDef documentedAlert = new AlertDef("ALERT-900", "test-service", "CRITICAL", "test alert");
        AlertDef undocumentedAlert = new AlertDef("ALERT-901", "test-service", "WARNING", "another test alert");
        RunbookStep step = new RunbookStep("ALERT-900", "page the test-service on-call");

        List<RunbookCoverageResult> results = new RunbookCoverageChecker()
                .evaluate(List.of(documentedAlert, undocumentedAlert), List.of(step));

        assertThat(results).hasSize(2);
        assertThat(results.get(0).documented()).isTrue();
        assertThat(results.get(1).documented()).isFalse();
        assertThat(results.get(1).reason()).contains("ALERT-901").contains("test-service");
    }

    private List<AlertDef> readCatalog() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream in = new ClassPathResource("runbook/alert_catalog.json").getInputStream()) {
            return mapper.readValue(in, mapper.getTypeFactory().constructCollectionType(List.class, AlertDef.class));
        }
    }

    private List<RunbookStep> readSteps() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream in = new ClassPathResource("runbook/runbook_steps.json").getInputStream()) {
            return mapper.readValue(in, mapper.getTypeFactory().constructCollectionType(List.class, RunbookStep.class));
        }
    }
}
