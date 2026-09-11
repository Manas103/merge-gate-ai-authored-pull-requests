"""Generates the 9 seeded zero-kill PR units (pr0246 through pr0254).

Each unit is a small two-branch implementation class plus a JUnit test class
that genuinely executes both branches (real coverage) but only asserts a
tautology (`result >= Integer.MIN_VALUE`), a real vacuous test: it compiles,
runs, and passes, yet no mutation of the arithmetic or the branch condition
can ever fail it, so every mutant PIT tries against these classes survives
and the real, measured mutation score is 0%. This is not a test rigged to
fail to compile or a score forced after the fact; it is run through the same
real `mvn org.pitest:pitest-maven:mutationCoverage` as the rest of the corpus.

These units are deliberately kept out of corpus_manifest.json /
branch_manifest.json (the original 245-PR-unit / 606-branch corpus that the
mutation-survival gate and its existing tests measure); they live in their
own zero_kill_corpus_manifest.json / zero_kill_manifest.json so the original
counts are untouched, and are evaluated by MutationScoreGate against the same
real PIT run (see docs/pit_mutation_report.txt and
docs/mutation_score_results.json).

Usage: python scripts/generate_zero_kill_units.py
"""
import json
import os

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
MAIN_JAVA = os.path.join(ROOT, "backend", "src", "main", "java", "com", "manas", "mergegate", "corpus")
TEST_JAVA = os.path.join(ROOT, "backend", "src", "test", "java", "com", "manas", "mergegate", "corpus")
RESOURCES = os.path.join(ROOT, "backend", "src", "main", "resources", "corpus")

UNITS = [
    {"num": 246, "class_name": "AlertLevel0246", "method": "classify", "param": "score",
     "threshold": 50, "expr_a": "score * 2", "expr_b": "score + 5",
     "desc": "alert level classification by raw score, deliberately vacuous test (zero-kill seed)"},
    {"num": 247, "class_name": "ShippingCost0247", "method": "compute", "param": "weight",
     "threshold": 20, "expr_a": "weight * 3 + 4", "expr_b": "weight * 2 + 50",
     "desc": "shipping cost by package weight, deliberately vacuous test (zero-kill seed)"},
    {"num": 248, "class_name": "InterestRate0248", "method": "compute", "param": "creditScore",
     "threshold": 650, "expr_a": "900 - creditScore", "expr_b": "creditScore - 400",
     "desc": "interest rate basis points by credit score, deliberately vacuous test (zero-kill seed)"},
    {"num": 249, "class_name": "DiscountTier0249", "method": "compute", "param": "amount",
     "threshold": 100, "expr_a": "amount / 2", "expr_b": "amount - 30",
     "desc": "discount amount by order total, deliberately vacuous test (zero-kill seed)"},
    {"num": 250, "class_name": "RiskScore0250", "method": "compute", "param": "flags",
     "threshold": 3, "expr_a": "flags * 10", "expr_b": "flags * 25",
     "desc": "risk score by flag count, deliberately vacuous test (zero-kill seed)"},
    {"num": 251, "class_name": "CreditLimit0251", "method": "compute", "param": "income",
     "threshold": 40000, "expr_a": "income / 10", "expr_b": "income / 6",
     "desc": "credit limit by annual income, deliberately vacuous test (zero-kill seed)"},
    {"num": 252, "class_name": "LatencyBucket0252", "method": "compute", "param": "millis",
     "threshold": 200, "expr_a": "millis + 1", "expr_b": "millis * 2",
     "desc": "latency bucket by measured milliseconds, deliberately vacuous test (zero-kill seed)"},
    {"num": 253, "class_name": "QueueTier0253", "method": "compute", "param": "depth",
     "threshold": 500, "expr_a": "depth + 20", "expr_b": "depth - 100",
     "desc": "queue tier by message depth, deliberately vacuous test (zero-kill seed)"},
    {"num": 254, "class_name": "RetryBackoff0254", "method": "compute", "param": "attempt",
     "threshold": 5, "expr_a": "attempt * 100", "expr_b": "500 + attempt * 50",
     "desc": "retry backoff millis by attempt number, deliberately vacuous test (zero-kill seed)"},
]

IMPL_TEMPLATE = """package com.manas.mergegate.corpus.pr{num:04d};

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class {class_name} {{

    private {class_name}() {{ }}

    public static int {method}(int {param}) {{
        if ({param} <= {threshold}) {{
            return {expr_a};
        }}
        else {{
            return {expr_b};
        }}
    }}
}}
"""

TEST_TEMPLATE = """package com.manas.mergegate.corpus.pr{num:04d};

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Deliberately vacuous: both branches are genuinely executed (real line
 * coverage), but the assertion is a tautology true of any int result, so no
 * mutation of the arithmetic or the branch condition can ever be caught.
 * This is the seeded zero-kill-suite case: the suite passes, the mutation
 * score is genuinely 0%. */
class {class_name}Test {{

    @Test
    void branch0ExecutesButAssertsATautology() {{
        int result = {class_name}.{method}({below});
        assertTrue(result >= Integer.MIN_VALUE);
    }}

    @Test
    void branch1ExecutesButAssertsATautology() {{
        int result = {class_name}.{method}({above});
        assertTrue(result >= Integer.MIN_VALUE);
    }}
}}
"""


def main():
    corpus_manifest = []
    branch_manifest = []

    for u in UNITS:
        num = u["num"]
        pr_id = f"pr{num:04d}"
        class_name = u["class_name"]
        below = max(0, u["threshold"] - 5)
        above = u["threshold"] + 25

        impl_dir = os.path.join(MAIN_JAVA, pr_id)
        test_dir = os.path.join(TEST_JAVA, pr_id)
        os.makedirs(impl_dir, exist_ok=True)
        os.makedirs(test_dir, exist_ok=True)

        impl_src = IMPL_TEMPLATE.format(num=num, class_name=class_name, method=u["method"],
                                         param=u["param"], threshold=u["threshold"],
                                         expr_a=u["expr_a"], expr_b=u["expr_b"])
        test_src = TEST_TEMPLATE.format(num=num, class_name=class_name, method=u["method"],
                                         below=below, above=above)

        with open(os.path.join(impl_dir, class_name + ".java"), "w", newline="\n") as f:
            f.write(impl_src)
        with open(os.path.join(test_dir, class_name + "Test.java"), "w", newline="\n") as f:
            f.write(test_src)

        package_name = f"com.manas.mergegate.corpus.{pr_id}"
        fqcn = f"{package_name}.{class_name}"
        corpus_manifest.append({
            "prId": pr_id,
            "className": class_name,
            "packageName": package_name,
            "source": "SEEDED_ZERO_KILL",
            "description": u["desc"],
        })
        branch_manifest.append({
            "prId": pr_id,
            "branchId": f"{pr_id}-b0",
            "className": class_name,
            "fqcn": fqcn,
            "lineNumber": 10,
            "description": u["desc"] + ", branch 0 (<= threshold)",
            "constructionQuality": "VACUOUS",
        })
        branch_manifest.append({
            "prId": pr_id,
            "branchId": f"{pr_id}-b1",
            "className": class_name,
            "fqcn": fqcn,
            "lineNumber": 13,
            "description": u["desc"] + ", branch 1 (> threshold)",
            "constructionQuality": "VACUOUS",
        })

    with open(os.path.join(RESOURCES, "zero_kill_corpus_manifest.json"), "w", newline="\n") as f:
        json.dump(corpus_manifest, f, indent=2)
        f.write("\n")
    with open(os.path.join(RESOURCES, "zero_kill_manifest.json"), "w", newline="\n") as f:
        json.dump(branch_manifest, f, indent=2)
        f.write("\n")

    print(f"generated {len(UNITS)} zero-kill PR units, {len(branch_manifest)} branches")


if __name__ == "__main__":
    main()
