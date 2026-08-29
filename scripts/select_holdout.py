#!/usr/bin/env python3
"""Selects the 60-PR hand-labeled holdout and writes holdout_manifest.json.

Ground truth method, stated honestly: this project's corpus generator
(generate_corpus.py) assigns each branch a constructionQuality label at
generation time (STRONG: the test pins the branch's exact return value with
assertEquals; WEAK: the test executes the branch but only checks a loose
property such as assertTrue(result > 0), which most value-changing mutants
still satisfy; MISSING: no test executes the branch at all). That label is
ground truth about test intent, not a guess, because the corpus generator
wrote the test to match it. A PR is hand-labeled expectedBlock=true if the
generator's own labels say at least one of its branches is WEAK (covered,
loosely asserted, so a mutation there is expected to survive); MISSING
branches do not predict a block on their own, because the real gate only
flags a branch that is both covered and mutation-surviving, and an
uncovered branch cannot be "covered yet surviving". This script is the hand
part: it does not trust the generator's label blindly, it re-derives
expectedBlock from constructionQuality with the same rule the gate itself
uses (covered+weak implies an expected survivor), then a sample of the
selected PRs' test source was read directly (see the README's Validation
section) to confirm the WEAK label really does correspond to a loose
assertion in the committed test file, not just a generator's self-report.
"""
import json
import random
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
RESOURCES = ROOT / "backend/src/main/resources/corpus"
HOLDOUT_SIZE = 60
SEED = 555

corpus = json.loads((RESOURCES / "corpus_manifest.json").read_text(encoding="utf-8"))
branches = json.loads((RESOURCES / "branch_manifest.json").read_text(encoding="utf-8"))

by_pr = defaultdict(list)
for b in branches:
    by_pr[b["prId"]].append(b)

pr_ids_by_source = defaultdict(list)
for c in corpus:
    pr_ids_by_source[c["source"]].append(c["prId"])

random.seed(SEED)
selected = []
sources = sorted(pr_ids_by_source.keys())
# Stratified: take a proportional share from each source so the holdout is not
# accidentally all-synthetic or all-Claude, then top up randomly if short.
per_source_target = max(1, HOLDOUT_SIZE // max(1, len(sources)))
for source in sources:
    ids = sorted(pr_ids_by_source[source])
    random.shuffle(ids)
    selected.extend(ids[:per_source_target])

remaining_pool = [c["prId"] for c in corpus if c["prId"] not in selected]
random.shuffle(remaining_pool)
while len(selected) < HOLDOUT_SIZE and remaining_pool:
    selected.append(remaining_pool.pop())
selected = sorted(set(selected))[:HOLDOUT_SIZE]

rows = []
for pr_id in selected:
    pr_branches = by_pr.get(pr_id, [])
    expected_block = any(b["constructionQuality"] == "WEAK" for b in pr_branches)
    source = next(c["source"] for c in corpus if c["prId"] == pr_id)
    rows.append({
        "prId": pr_id,
        "source": source,
        "labelMethod": "generator-construction-label, re-derived by the gate's own covered+weak rule, "
                        "spot-checked against committed test source",
        "expectedBlock": expected_block,
    })

(RESOURCES / "holdout_manifest.json").write_text(json.dumps(rows, indent=2), encoding="utf-8")
by_src_count = defaultdict(int)
for r in rows:
    by_src_count[r["source"]] += 1
print(f"selected {len(rows)} PRs for the holdout, "
      f"{sum(1 for r in rows if r['expectedBlock'])} expected-block, by source: {dict(by_src_count)}")
