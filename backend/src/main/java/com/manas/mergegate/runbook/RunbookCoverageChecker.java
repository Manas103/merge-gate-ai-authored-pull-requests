package com.manas.mergegate.runbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The runbook-coverage half of the release gate. Given the full alert catalog
 * and the runbook step registry, flags every alert whose service can raise it
 * with no documented runbook step. A release is blocked if any alert it can
 * raise is flagged; this class only computes the per-alert decision, mirroring
 * how gate.GateEngine computes per-branch decisions before a PR-level rollup.
 */
public final class RunbookCoverageChecker {

    public List<RunbookCoverageResult> evaluate(List<AlertDef> alerts, List<RunbookStep> steps) {
        Map<String, RunbookStep> byAlertId = steps.stream()
                .collect(Collectors.toMap(RunbookStep::alertId, s -> s, (a, b) -> a));
        List<RunbookCoverageResult> out = new ArrayList<>();
        for (AlertDef alert : alerts) {
            out.add(RunbookCoverageResult.of(alert, byAlertId.get(alert.alertId())));
        }
        return out;
    }

    /** Alerts with no documented runbook step: the specific gap this rule blocks a merge for. */
    public List<RunbookCoverageResult> flagged(List<AlertDef> alerts, List<RunbookStep> steps) {
        return evaluate(alerts, steps).stream()
                .filter(r -> !r.documented())
                .toList();
    }
}
