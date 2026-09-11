package com.manas.mergegate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/** Persisted RunbookCoverageResult row for one alert: whether the service
 * that can raise it has a documented runbook step. */
@Entity
public class AlertCoverage {
    @Id
    private String alertId;
    private String service;
    private String severity;
    private String description;
    private boolean documented;
    private String reason;

    protected AlertCoverage() { }

    public AlertCoverage(String alertId, String service, String severity, String description,
                          boolean documented, String reason) {
        this.alertId = alertId;
        this.service = service;
        this.severity = severity;
        this.description = description;
        this.documented = documented;
        this.reason = reason;
    }

    public String getAlertId() { return alertId; }
    public String getService() { return service; }
    public String getSeverity() { return severity; }
    public String getDescription() { return description; }
    public boolean isDocumented() { return documented; }
    public String getReason() { return reason; }
}
