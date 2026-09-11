package com.manas.mergegate.runbook;

/** The gate's decision for one alert: real output of intersecting the alert
 * catalog with the runbook step registry. documented is false exactly when
 * no RunbookStep exists for this alertId, meaning a release that can trigger
 * this alert would page someone with no documented response. */
public record RunbookCoverageResult(
        String alertId,
        String service,
        String severity,
        String description,
        boolean documented,
        String reason) {

    public static RunbookCoverageResult of(AlertDef def, RunbookStep step) {
        boolean documented = step != null;
        String reason = documented
                ? "documented runbook step present: " + step.step()
                : "no documented runbook step for " + def.alertId() + " (" + def.service() + ")";
        return new RunbookCoverageResult(def.alertId(), def.service(), def.severity(),
                def.description(), documented, reason);
    }
}
