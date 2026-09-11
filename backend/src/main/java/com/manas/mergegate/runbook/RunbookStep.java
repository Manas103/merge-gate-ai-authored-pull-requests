package com.manas.mergegate.runbook;

/** One documented runbook step from runbook_steps.json, keyed by the alert
 * id it responds to. Not every alert in the catalog has one; the alerts with
 * no matching RunbookStep are exactly the seeded documentation gap this gate
 * exists to catch. */
public record RunbookStep(String alertId, String step) {
}
