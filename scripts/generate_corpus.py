#!/usr/bin/env python3
"""Generates the PR corpus for the merge gate project.

Each "PR unit" is one small Java implementation class plus its JUnit 5 test
class, living under com.manas.mergegate.corpus.prNNNN. Every unit is tagged
with a provenance source:

  CLAUDE_CODE        genuinely produced by a live `claude -p` call (see
                      generate_claude_batch.py, run separately since it needs
                      the network/CLI and is slow).
  SYNTHETIC_CURSOR    scripted generator, labeled Cursor-style for corpus
                      bookkeeping only. Same generation logic as SYNTHETIC_COPILOT.
  SYNTHETIC_COPILOT   scripted generator, labeled Copilot-style for corpus
                      bookkeeping only. Same generation logic as SYNTHETIC_CURSOR.

This script only writes the SYNTHETIC_* units (deterministic, seeded, no
network calls). Run generate_claude_batch.py separately to add the
CLAUDE_CODE units on top. Both scripts append to the same manifest file
(corpus_manifest.json) and the same branch registry (branch_manifest.json)
under backend/src/main/resources/corpus/, keyed by pr id, so run order is
generate_corpus.py first (creates the files) then generate_claude_batch.py
(appends).
"""
import json
import random
import textwrap
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
BACKEND = ROOT / "backend"
MAIN_JAVA = BACKEND / "src/main/java/com/manas/mergegate/corpus"
TEST_JAVA = BACKEND / "src/test/java/com/manas/mergegate/corpus"
RESOURCES = BACKEND / "src/main/resources/corpus"

SEED = 20260828
NUM_SYNTHETIC = 230

# Each kind: (name, param_name, description, list of (base, mult) coefficient
# templates used per branch in order). Actual coefficients are jittered per
# instance so the corpus is not 230 literal copies of 16 formulas.
KINDS = [
    ("ShippingFee", "weightKg", "shipping fee tier by parcel weight in kg"),
    ("LateFee", "daysLate", "late fee schedule by days overdue"),
    ("GradeBand", "scorePct", "grade band score adjustment by percent score"),
    ("TempZone", "tempC", "climate control output by temperature in Celsius"),
    ("AgeMultiplier", "ageYears", "pricing multiplier by age in years"),
    ("DiscountTier", "purchaseCents", "discount tier by purchase amount in cents"),
    ("PasswordScore", "length", "password strength score by character length"),
    ("RetryBackoff", "attempt", "retry backoff milliseconds by attempt number"),
    ("BmiZone", "bmiTenths", "BMI zone adjustment by BMI in tenths"),
    ("TaxBracket", "incomeCents", "tax withholding by income in cents"),
    ("QueuePriority", "waitMinutes", "queue priority score by wait minutes"),
    ("InventoryReorder", "unitsLeft", "reorder quantity by units left in stock"),
    ("LatencyBudget", "millis", "latency budget classification by measured millis"),
    ("BatterySaver", "percent", "power plan selection by battery percent"),
    ("ErrorSeverity", "code", "error severity score by numeric error code"),
    ("ShiftPay", "hoursWorked", "shift pay multiplier by hours worked"),
    ("CreditRisk", "scorePoints", "credit risk band by score points"),
    ("TrafficLight", "vehiclesPerMin", "signal timing adjustment by vehicles per minute"),
]


def pick_branch_count(rng):
    r = rng.random()
    if r < 0.65:
        return 2
    if r < 0.95:
        return 3
    return 4


def build_instance(rng, kind_idx, pr_index):
    name, param, desc = KINDS[kind_idx % len(KINDS)]
    n_branches = pick_branch_count(rng)
    n_cuts = n_branches - 1
    # ascending distinct cut points
    cuts = sorted(rng.sample(range(1, 400), n_cuts))
    class_name = f"{name}{pr_index:04d}"
    branches = []
    lo = 0
    for i, cut in enumerate(cuts):
        base = rng.randint(10, 90) + i * rng.randint(5, 15)
        mult = rng.randint(2, 11)
        branches.append({
            "cond": f"{param} <= {cut}",
            "lo": lo,
            "hi": cut,
            "base": base,
            "mult": mult,
            "desc": f"{desc}, band {i + 1} ({param} <= {cut})",
        })
        lo = cut + 1
    base = rng.randint(100, 300) + n_cuts * rng.randint(10, 20)
    mult = rng.randint(2, 11)
    branches.append({
        "cond": None,
        "lo": lo,
        "hi": lo + rng.randint(50, 400),
        "base": base,
        "mult": mult,
        "desc": f"{desc}, default/overflow band ({param} > {cuts[-1]})",
    })
    return class_name, param, desc, branches


def assign_quality(rng, branches):
    """Assigns STRONG / WEAK / MISSING per branch, biased like real shallow
    AI-assistant test suites: the first (happy-path) branch is almost always
    strongly tested, later/edge branches are more often weak or missing."""
    qualities = []
    for i, b in enumerate(branches):
        is_last = i == len(branches) - 1
        r = rng.random()
        if i == 0:
            q = "STRONG" if r < 0.85 else "WEAK"
        elif is_last:
            if r < 0.40:
                q = "STRONG"
            elif r < 0.75:
                q = "WEAK"
            else:
                q = "MISSING"
        else:
            if r < 0.55:
                q = "STRONG"
            elif r < 0.85:
                q = "WEAK"
            else:
                q = "MISSING"
        qualities.append(q)
    return qualities


def render_impl(pkg, class_name, param, branches):
    lines = []
    lines.append(f"package {pkg};")
    lines.append("")
    lines.append("/** Generated PR unit. See corpus_manifest.json for provenance. */")
    lines.append(f"public final class {class_name} {{")
    lines.append("")
    lines.append(f"    private {class_name}() {{ }}")
    lines.append("")
    lines.append(f"    public static int compute(int {param}) {{")
    branch_lines = []
    for i, b in enumerate(branches):
        if b["cond"] is not None:
            kw = "if" if i == 0 else "else if"
            lines.append(f"        {kw} ({b['cond']}) {{")
        else:
            lines.append("        else {")
        lines.append(f"            return {b['base']} + {param} * {b['mult']};")
        branch_lines.append(len(lines))
        lines.append("        }")
    lines.append("    }")
    lines.append("}")
    lines.append("")
    return "\n".join(lines), branch_lines


def render_test(pkg, class_name, param, branches, qualities, rng):
    lines = []
    lines.append(f"package {pkg};")
    lines.append("")
    lines.append("import org.junit.jupiter.api.Test;")
    lines.append("import static org.junit.jupiter.api.Assertions.*;")
    lines.append("")
    lines.append(f"class {class_name}Test {{")
    lines.append("")
    for i, (b, q) in enumerate(zip(branches, qualities)):
        if q == "MISSING":
            continue
        x = rng.randint(b["lo"], b["hi"])
        expected = b["base"] + x * b["mult"]
        lines.append(f"    @Test")
        lines.append(f"    void branch{i}() {{")
        lines.append(f"        int result = {class_name}.compute({x});")
        if q == "STRONG":
            lines.append(f"        assertEquals({expected}, result);")
        else:  # WEAK: executes the branch but the assertion does not pin
               # the exact arithmetic, so most single-operator mutations
               # of that branch's line still pass.
            lines.append(f"        assertTrue(result > 0);")
        lines.append("    }")
        lines.append("")
    lines.append("}")
    lines.append("")
    return "\n".join(lines)


def main():
    rng = random.Random(SEED)
    MAIN_JAVA.mkdir(parents=True, exist_ok=True)
    TEST_JAVA.mkdir(parents=True, exist_ok=True)
    RESOURCES.mkdir(parents=True, exist_ok=True)

    corpus_manifest = []
    branch_manifest = []

    for idx in range(1, NUM_SYNTHETIC + 1):
        pr_id = f"pr{idx:04d}"
        source = "SYNTHETIC_CURSOR" if idx % 2 == 0 else "SYNTHETIC_COPILOT"
        pkg = f"com.manas.mergegate.corpus.{pr_id}"
        class_name, param, desc, branches = build_instance(rng, idx, idx)
        qualities = assign_quality(rng, branches)

        impl_src, branch_lines = render_impl(pkg, class_name, param, branches)
        test_src = render_test(pkg, class_name, param, branches, qualities, rng)

        pkg_dir_main = MAIN_JAVA / pr_id
        pkg_dir_test = TEST_JAVA / pr_id
        pkg_dir_main.mkdir(parents=True, exist_ok=True)
        pkg_dir_test.mkdir(parents=True, exist_ok=True)
        (pkg_dir_main / f"{class_name}.java").write_text(impl_src, encoding="utf-8")
        (pkg_dir_test / f"{class_name}Test.java").write_text(test_src, encoding="utf-8")

        corpus_manifest.append({
            "prId": pr_id,
            "className": class_name,
            "packageName": pkg,
            "source": source,
            "description": desc,
        })
        for bi, (b, q) in enumerate(zip(branches, qualities)):
            branch_manifest.append({
                "prId": pr_id,
                "branchId": f"{pr_id}-b{bi}",
                "className": class_name,
                "fqcn": f"{pkg}.{class_name}",
                "lineNumber": branch_lines[bi],
                "description": b["desc"],
                "constructionQuality": q,
            })

    (RESOURCES / "corpus_manifest.json").write_text(
        json.dumps(corpus_manifest, indent=2), encoding="utf-8")
    (RESOURCES / "branch_manifest.json").write_text(
        json.dumps(branch_manifest, indent=2), encoding="utf-8")

    print(f"wrote {len(corpus_manifest)} synthetic PR units, "
          f"{len(branch_manifest)} branches")


if __name__ == "__main__":
    main()
