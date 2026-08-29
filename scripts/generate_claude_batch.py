#!/usr/bin/env python3
"""Genuinely invokes `claude -p` to produce a live sample of the PR corpus.

This is the CLAUDE_CODE-sourced slice of the corpus: real headless calls to
the Claude Code CLI, each asked to write one small Java method plus its own
JUnit test, with no instruction about test quality or edge-case coverage, so
whatever coverage-vs-mutation-strength gap shows up is Claude's own organic
choice, not scripted. Raw stdout from every call is saved under
docs/claude_raw/ for inspection. Units that fail to parse into two clean,
matching, brace-balanced fenced code blocks are skipped and logged; the
script keeps calling further tasks from TASKS until it has produced
TARGET_SUCCESS parsed units or run out of tasks, and reports the exact
attempted/succeeded counts at the end. That reported split is what the
README's honest-framing section states.
"""
import json
import re
import subprocess
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
BACKEND = ROOT / "backend"
MAIN_JAVA = BACKEND / "src/main/java/com/manas/mergegate/corpus"
TEST_JAVA = BACKEND / "src/test/java/com/manas/mergegate/corpus"
RESOURCES = BACKEND / "src/main/resources/corpus"
RAW_DIR = ROOT / "docs/claude_raw"

START_INDEX = 231
TARGET_SUCCESS = 15

TASKS = [
    "a late fee schedule in cents based on minutesLate",
    "a shipping insurance premium in cents based on declaredValueHundreds",
    "a customer loyalty point bonus based on ordersThisYear",
    "an elevator dispatch priority score based on floorsAway",
    "a thermostat energy mode score based on occupancySensorsActive",
    "a warehouse pick-path priority based on aisleNumber",
    "a support ticket urgency score based on hoursSinceLastReply",
    "a video transcode priority based on queueDepth",
    "a coupon stacking discount in cents based on itemCount",
    "a battery charge rate limit based on temperatureTenthsC",
    "a fraud review score based on failedLoginAttempts",
    "a delivery route surcharge in cents based on stopsRemaining",
    "a parking garage rate in cents based on minutesParked",
    "a server autoscale target based on requestsPerSecond",
    "a loan interest rate adjustment in basis points based on creditScorePoints",
    "a hospital triage priority based on heartRateBpm",
    "a subscription renewal discount in cents based on monthsSubscribed",
    "a shipment customs risk score based on declaredWeightKg",
    "a game matchmaking rating adjustment based on winStreak",
    "a call center staffing level based on callsWaiting",
    "a irrigation valve duration in seconds based on soilMoisturePercent",
    "a cache eviction priority based on lastAccessMinutesAgo",
    "a flight boarding group score based on frequentFlyerTier",
    "an ad bid multiplier in cents based on clickThroughRatePermille",
    "a restaurant table wait estimate in minutes based on partySize",
    "a solar panel output adjustment based on cloudCoverPercent",
    "a warranty claim payout in cents based on monthsSincePurchase",
    "a spam filter score based on linkCountInMessage",
    "a gym membership tier fee in cents based on visitsPerMonth",
    "a rideshare surge multiplier in cents based on activeRequests",
    "a factory conveyor speed setting based on itemsPerMinute",
    "a helpdesk escalation score based on reopenCount",
    "a vaccine dose interval in days based on ageYears",
    "a bandwidth throttle level based on concurrentStreams",
    "a payroll overtime multiplier in cents based on hoursOverWeek",
]

BLOCK_RE = re.compile(r"```(?:java)?\s*\n(.*?)```", re.DOTALL)
BRANCH_START_RE = re.compile(r"^\s*\}?\s*(if\s*\(|else if\s*\(|else\s*\{)")


def build_prompt(class_name, pkg, desc):
    return (
        f"Write a small Java 21 static utility class in package {pkg} named "
        f"{class_name} with a single public static int method compute(int value) "
        f"that computes {desc}, using a plain if / else if / else chain with 2 to 4 "
        f"branches over ranges of value (no switch, no loops, no helper methods, no "
        f"nested ifs, no exceptions, no comments). Also write its JUnit 5 test class "
        f"{class_name}Test in the same package, a normal test class as you would "
        f"ordinarily write one. Output ONLY two fenced java code blocks, first the "
        f"impl class, second the test class. No prose before or after."
    )


def find_branch_lines(impl_src, class_name):
    # The generation prompt requires a flat if/else-if/else chain with no
    # nesting, so each branch header's own return line is simply the next
    # line containing "return" after that header, in source order. The
    # original implementation tracked brace depth and toggled an
    # in_branch_block flag around the whole chain; that mis-detected a
    # "} else if (...) {" header as a brace-depth no-op (one "}" closing the
    # previous arm and one "{" opening the next cancel out), so it never
    # noticed the chain had moved to a new arm and collapsed every
    # if/else-if/else chain down to a single captured branch line. See the
    # README's Findings section for the root-cause story.
    lines = impl_src.split("\n")
    branch_lines = []
    in_method = False
    for i, line in enumerate(lines, start=1):
        if not in_method:
            if "compute(int" in line and "{" in line:
                in_method = True
            continue
        if BRANCH_START_RE.match(line):
            for k in range(i, len(lines)):
                if "return" in lines[k].strip():
                    branch_lines.append(k + 1)
                    break
    return branch_lines




def main():
    MAIN_JAVA.mkdir(parents=True, exist_ok=True)
    TEST_JAVA.mkdir(parents=True, exist_ok=True)
    RAW_DIR.mkdir(parents=True, exist_ok=True)

    corpus_manifest = json.loads((RESOURCES / "corpus_manifest.json").read_text(encoding="utf-8"))
    branch_manifest = json.loads((RESOURCES / "branch_manifest.json").read_text(encoding="utf-8"))

    attempted = 0
    succeeded = 0
    failures = []
    idx = START_INDEX

    for task_i, desc in enumerate(TASKS):
        if succeeded >= TARGET_SUCCESS:
            break
        attempted += 1
        pr_id = f"pr{idx:04d}"
        pkg = f"com.manas.mergegate.corpus.{pr_id}"
        class_name = f"ClaudeTask{idx:04d}"
        prompt = build_prompt(class_name, pkg, desc)

        proc = subprocess.run(
            ["claude", "-p", prompt],
            capture_output=True, text=True, timeout=180,
        )
        raw = proc.stdout
        (RAW_DIR / f"{pr_id}.txt").write_text(raw, encoding="utf-8")

        blocks = BLOCK_RE.findall(raw)
        ok = False
        if len(blocks) >= 2:
            impl_src, test_src = blocks[0], blocks[1]
            if (f"class {class_name}" in impl_src and f"class {class_name}Test" in test_src
                    and impl_src.count("{") == impl_src.count("}")
                    and test_src.count("{") == test_src.count("}")):
                branch_lines = find_branch_lines(impl_src, class_name)
                if len(branch_lines) >= 2:
                    pkg_dir_main = MAIN_JAVA / pr_id
                    pkg_dir_test = TEST_JAVA / pr_id
                    pkg_dir_main.mkdir(parents=True, exist_ok=True)
                    pkg_dir_test.mkdir(parents=True, exist_ok=True)
                    (pkg_dir_main / f"{class_name}.java").write_text(impl_src.strip() + "\n", encoding="utf-8")
                    (pkg_dir_test / f"{class_name}Test.java").write_text(test_src.strip() + "\n", encoding="utf-8")

                    corpus_manifest.append({
                        "prId": pr_id,
                        "className": class_name,
                        "packageName": pkg,
                        "source": "CLAUDE_CODE",
                        "description": desc,
                    })
                    fqcn = f"{pkg}.{class_name}"
                    for bi, ln in enumerate(branch_lines):
                        branch_manifest.append({
                            "prId": pr_id,
                            "branchId": f"{pr_id}-b{bi}",
                            "className": class_name,
                            "fqcn": fqcn,
                            "lineNumber": ln,
                            "description": f"{desc}, branch {bi + 1} (claude-authored)",
                            "constructionQuality": "UNKNOWN_LIVE",
                        })
                    ok = True
        if ok:
            succeeded += 1
            print(f"[{pr_id}] OK task={task_i} branches={len(branch_lines)}")
            idx += 1
        else:
            failures.append({"task": desc, "raw_len": len(raw), "blocks": len(blocks)})
            print(f"[{pr_id}] PARSE FAILED task={task_i} blocks={len(blocks)}")

    (RESOURCES / "corpus_manifest.json").write_text(json.dumps(corpus_manifest, indent=2), encoding="utf-8")
    (RESOURCES / "branch_manifest.json").write_text(json.dumps(branch_manifest, indent=2), encoding="utf-8")

    summary = {"attempted": attempted, "succeeded": succeeded, "failures": failures}
    (RAW_DIR / "_summary.json").write_text(json.dumps(summary, indent=2), encoding="utf-8")
    print(json.dumps(summary, indent=2))


if __name__ == "__main__":
    main()
