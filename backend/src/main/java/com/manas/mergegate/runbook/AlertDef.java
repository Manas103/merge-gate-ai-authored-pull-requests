package com.manas.mergegate.runbook;

/** One alert from alert_catalog.json: a service that can page an on-call
 * engineer, at a given severity. This catalog is independent of the
 * PR/mutation corpus; it models the release-gate's second rule, that a
 * service which can raise an alert must have a documented runbook step for
 * it before a release is allowed to merge. */
public record AlertDef(
        String alertId,
        String service,
        String severity,
        String description) {
}
