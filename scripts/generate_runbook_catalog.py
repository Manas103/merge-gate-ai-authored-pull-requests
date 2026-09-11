"""Generates the release gate's alert catalog and runbook step registry.

Deterministic and seeded, like generate_corpus.py: 42 alert definitions spread
across 12 representative services, and a runbook step for exactly 24 of them.
The other 18 are a genuine, seeded gap (random.sample with a fixed seed,
computed once and committed; not hand-picked after the fact to hit 18). This
catalog is a synthetic representation of a bank's alerting surface, not a real
production catalog; see the README's "Honest framing" section.

Usage: python scripts/generate_runbook_catalog.py
Writes:
  backend/src/main/resources/runbook/alert_catalog.json   (42 alerts)
  backend/src/main/resources/runbook/runbook_steps.json   (24 documented steps)
"""
import json
import os
import random

SERVICES = [
    "payments-service", "ledger-service", "auth-service", "notification-service",
    "settlement-service", "fraud-service", "reconciliation-service", "reporting-service",
    "gateway-service", "batch-processing-service", "card-issuance-service", "risk-scoring-service",
]

SEVERITIES = ["CRITICAL", "WARNING", "INFO"]

ALERT_KINDS = [
    "latency p99 exceeds SLO", "error rate exceeds threshold", "queue depth exceeds threshold",
    "disk usage exceeds threshold", "circuit breaker opened", "downstream timeout rate exceeds threshold",
    "replication lag exceeds threshold", "reconciliation mismatch detected",
    "auth token issuance failure rate exceeds threshold", "batch job overrun",
    "certificate expiry approaching", "connection pool exhaustion",
    "dead letter queue depth exceeds threshold", "webhook delivery failure rate exceeds threshold",
]

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT_DIR = os.path.join(ROOT, "backend", "src", "main", "resources", "runbook")


def main():
    random.seed(42)
    alerts = []
    for i in range(1, 43):
        service = SERVICES[(i - 1) % len(SERVICES)]
        severity = SEVERITIES[(i - 1) % len(SEVERITIES)]
        kind = ALERT_KINDS[(i - 1) % len(ALERT_KINDS)]
        alert_id = f"ALERT-{i:03d}"
        alerts.append({
            "alertId": alert_id,
            "service": service,
            "severity": severity,
            "description": f"{service} {kind}",
        })

    alert_ids = [a["alertId"] for a in alerts]
    missing = set(random.sample(alert_ids, 18))

    steps = []
    for a in alerts:
        if a["alertId"] in missing:
            continue
        topic = a["description"].split(" ", 1)[1]
        steps.append({
            "alertId": a["alertId"],
            "step": (
                f"page the {a['service']} on-call, check the {a['service']} dashboard for "
                f"{topic}, and follow the {a['service']} runbook section for this alert class"
            ),
        })

    os.makedirs(OUT_DIR, exist_ok=True)
    with open(os.path.join(OUT_DIR, "alert_catalog.json"), "w", newline="\n") as f:
        json.dump(alerts, f, indent=2)
        f.write("\n")
    with open(os.path.join(OUT_DIR, "runbook_steps.json"), "w", newline="\n") as f:
        json.dump(steps, f, indent=2)
        f.write("\n")

    print(f"alerts: {len(alerts)}, documented: {len(steps)}, missing: {len(missing)}")
    print("missing ids:", ", ".join(sorted(missing)))


if __name__ == "__main__":
    main()
