"""Generates the security-and-mutation review gate's seeded security benchmark.

Deterministic and seeded, like generate_corpus.py and generate_runbook_catalog.py: 60 cases,
20 each for the three new rules (taint analysis into SQL/shell sinks, a missing-authorization
diff on new endpoints, dependency provenance). Each rule's 20 cases are 10 deliberately
vulnerable/hallucinated and 10 deliberately safe/real, so the rule's catch rate and
false-positive rate are both measurable, not just its catch rate. Code snippets are
synthetic Java, not drawn from a real codebase; see the README's "Honest framing" section.

Usage: python scripts/generate_security_corpus.py
Writes: backend/src/main/resources/security/security_corpus.json (60 cases)
"""
import json
import os
import random

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT_DIR = os.path.join(ROOT, "backend", "src", "main", "resources", "security")

TABLES = ["accounts", "invoices", "merchants", "tickets", "sessions"]
COLUMNS = ["account_id", "email", "status", "owner_id", "reference"]
PARAM_NAMES = ["requestId", "accountRef", "lookupKey", "searchTerm", "customerId"]
SHELL_TARGETS = ["host", "hostname", "node", "target", "address"]

VULN_SQL_TEMPLATE = """public List<Row> lookup{n}(@RequestParam String {param}) {{
    Statement stmt = connection.createStatement();
    return stmt.executeQuery("SELECT * FROM {table} WHERE {column} = '" + {param} + "'");
}}"""

SAFE_SQL_TEMPLATE = """public List<Row> lookup{n}(@RequestParam String {param}) {{
    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM {table} WHERE {column} = ?");
    stmt.setString(1, {param});
    return stmt.executeQuery();
}}"""

VULN_SHELL_TEMPLATE = """public String ping{n}(@RequestParam String {param}) throws IOException {{
    Process p = Runtime.getRuntime().exec("ping -c 1 " + {param});
    return readOutput(p);
}}"""

SAFE_SHELL_TEMPLATE = """public String ping{n}(@RequestParam String {param}) throws IOException {{
    ProcessBuilder pb = new ProcessBuilder("ping", "-c", "1", "localhost");
    Process proc = pb.start();
    return readOutput(proc);
}}"""

VULN_ENDPOINT_TEMPLATE = """@RestController
public class Endpoint{n}Controller {{
    @{httpMethod}Mapping("/api/resource{n}")
    public ResponseEntity<Resource{n}> get{n}(@PathVariable String id) {{
        return ResponseEntity.ok(service.find(id));
    }}
}}"""

SAFE_ENDPOINT_TEMPLATE = """@RestController
public class Endpoint{n}Controller {{
    @PreAuthorize("hasRole('{role}')")
    @{httpMethod}Mapping("/api/resource{n}")
    public ResponseEntity<Resource{n}> get{n}(@PathVariable String id) {{
        return ResponseEntity.ok(service.find(id));
    }}
}}"""

HTTP_METHODS = ["Get", "Post", "Put", "Delete"]
ROLES = ["ADMIN", "OPS", "SUPPORT", "BILLING"]

REAL_PACKAGES = [
    "spring-boot-starter-web", "spring-boot-starter-data-jpa", "jackson-databind",
    "lombok", "junit-jupiter", "mockito-core", "guava", "commons-lang3",
    "slf4j-api", "h2",
]

HALLUCINATED_PACKAGES = [
    "spring-boot-starter-webx", "spring-boot-starter-securex", "jacksonutils-core",
    "commons-lang4", "guava-extra-collections", "lombok-plus", "junit-jupiter-fast",
    "mockito-core-lite", "slf4j-simple-fast", "h2-turbo",
]


def build_taint_cases():
    cases = []
    for i in range(5):
        cases.append({
            "id": f"TAINT-{i + 1:02d}",
            "ruleType": "taint",
            "snippet": VULN_SQL_TEMPLATE.format(n=i + 1, param=PARAM_NAMES[i], table=TABLES[i], column=COLUMNS[i]),
            "expectedFlag": True,
            "label": "vulnerable-sql-concat",
        })
    for i in range(5):
        cases.append({
            "id": f"TAINT-{i + 6:02d}",
            "ruleType": "taint",
            "snippet": VULN_SHELL_TEMPLATE.format(n=i + 1, param=SHELL_TARGETS[i]),
            "expectedFlag": True,
            "label": "vulnerable-shell-concat",
        })
    for i in range(5):
        cases.append({
            "id": f"TAINT-{i + 11:02d}",
            "ruleType": "taint",
            "snippet": SAFE_SQL_TEMPLATE.format(n=i + 1, param=PARAM_NAMES[i], table=TABLES[i], column=COLUMNS[i]),
            "expectedFlag": False,
            "label": "safe-prepared-statement",
        })
    for i in range(5):
        cases.append({
            "id": f"TAINT-{i + 16:02d}",
            "ruleType": "taint",
            "snippet": SAFE_SHELL_TEMPLATE.format(n=i + 1, param=SHELL_TARGETS[i]),
            "expectedFlag": False,
            "label": "safe-fixed-command",
        })
    return cases


def build_authz_cases():
    cases = []
    for i in range(10):
        http_method = HTTP_METHODS[i % len(HTTP_METHODS)]
        cases.append({
            "id": f"AUTHZ-{i + 1:02d}",
            "ruleType": "authz",
            "snippet": VULN_ENDPOINT_TEMPLATE.format(n=i + 1, httpMethod=http_method),
            "expectedFlag": True,
            "label": "missing-authorization",
        })
    for i in range(10):
        http_method = HTTP_METHODS[i % len(HTTP_METHODS)]
        role = ROLES[i % len(ROLES)]
        cases.append({
            "id": f"AUTHZ-{i + 11:02d}",
            "ruleType": "authz",
            "snippet": SAFE_ENDPOINT_TEMPLATE.format(n=i + 11, httpMethod=http_method, role=role),
            "expectedFlag": False,
            "label": "authorized-endpoint",
        })
    return cases


def build_provenance_cases():
    cases = []
    for i, name in enumerate(HALLUCINATED_PACKAGES):
        cases.append({
            "id": f"PROV-{i + 1:02d}",
            "ruleType": "provenance",
            "snippet": name,
            "expectedFlag": True,
            "label": "hallucinated-package",
        })
    for i, name in enumerate(REAL_PACKAGES):
        cases.append({
            "id": f"PROV-{i + 11:02d}",
            "ruleType": "provenance",
            "snippet": name,
            "expectedFlag": False,
            "label": "real-package",
        })
    return cases


def main():
    random.seed(42)
    cases = build_taint_cases() + build_authz_cases() + build_provenance_cases()

    os.makedirs(OUT_DIR, exist_ok=True)
    with open(os.path.join(OUT_DIR, "security_corpus.json"), "w", newline="\n") as f:
        json.dump(cases, f, indent=2)
        f.write("\n")

    print(f"cases: {len(cases)}")
    for rule in ("taint", "authz", "provenance"):
        rule_cases = [c for c in cases if c["ruleType"] == rule]
        expected_true = sum(1 for c in rule_cases if c["expectedFlag"])
        print(f"  {rule}: {len(rule_cases)} cases, {expected_true} expected-flag")


if __name__ == "__main__":
    main()
