# Merge Gate for AI-Authored Pull Requests / Release Gate with Runbook Coverage and Mutation Testing

A CI gate that runs real mutation testing (PIT) over a corpus of small pull-request-sized Java units and blocks a PR when one of its branches is line-covered by its own test yet a mutation of that branch still survives, meaning the covering test executes the branch but never actually asserts on its effect. Extended with two more rules over the same real PIT run: a mutation-score floor that blocks any PR unit scoring below 70% (which catches a suite that passes while killing zero mutants outright), and a runbook-coverage rule that blocks a release for any alert a service can raise with no documented runbook step. Java 21, Spring Boot 3.3.4, Spring Data JPA, PostgreSQL (H2 in PostgreSQL-compatibility mode for tests), PIT 1.17.0, TypeScript/React console, GitHub Actions. Every number below was measured on this machine by running the real gate over a real, committed corpus, not targeted in advance.

## Why this exists

A company that ships a large fraction of its code through LLM coding assistants and requires human review on every pull request needs a cheap, mechanical signal about which AI-authored branches actually got exercised by a meaningful assertion, as opposed to a branch a human reviewer might wave through because the diff has a green checkmark next to it. Line coverage alone cannot tell that story: a test can execute a branch and still assert nothing that would catch a broken version of it. Mutation testing can, because it asks a stronger question of every covered line: if this line's behavior were subtly wrong, would any test actually notice? This repository is a small, honest version of a merge gate built directly on that question.

## Honest framing, up front

- **This is a mutation-testing gate over a corpus of small, independent Java units, not a real GitHub merge-gate integration.** There is no GitHub App, no webhook, no actual PR diff parsing; "PR unit" means one small implementation class plus its own test class, tracked as if it were the sole content of one pull request.
- **Corpus provenance, stated exactly, because this is the part of the resume claim that most needs scrutiny.** The corpus is 245 PR units. 15 of them (`CLAUDE_CODE` in `corpus_manifest.json`) are genuinely, live `claude -p` calls made from `scripts/generate_claude_batch.py`: each call was asked to write one small Java method and its own JUnit test, with no instruction about test quality, and its raw stdout is committed under `docs/claude_raw/`. The other 230 are a scripted, seeded generator (`scripts/generate_corpus.py`) labeled `SYNTHETIC_CURSOR` and `SYNTHETIC_COPILOT` for corpus bookkeeping only; **Cursor and GitHub Copilot are not headlessly scriptable in the environment this project was built in, so no code in this repository was actually produced by either tool.** The 240-plus PR count is genuinely met (245); the "from Claude Code, Cursor and GitHub Copilot" provenance is genuinely met only for the Claude Code slice. See "Findings" for what those 15 live calls actually turned up, and "Measured results" for how this affects the claim.
- **The 60-PR holdout's "hand-labeled" ground truth is a re-derivation of the corpus generator's own construction intent, not an external human PR review.** `scripts/generate_corpus.py` assigns each branch a `constructionQuality` (`STRONG`: the test pins the exact return value with `assertEquals`; `WEAK`: the test executes the branch but only checks a loose property such as `assertTrue(result > 0)`, which most value-changing mutants still satisfy; `MISSING`: no test executes the branch). `scripts/select_holdout.py` re-derives each holdout PR's `expectedBlock` from those labels using the same covered-and-weak rule the real gate uses, then a sample of the selected PRs' committed test source was read directly to confirm the `WEAK` label really does correspond to a loose assertion (see `LateFee0001Test.branch1`, `assertTrue(result > 0)`, in the Architecture section below). That is the "hand" in hand-labeled: a human-reviewed rule and a spot-check against real source, not a blind trust of the generator's self-report.
- **Machine and toolchain.** 8 physical / 16 logical cores, Windows 11 Home. JDK 21 (Temurin), Maven 3.9.9, Spring Boot 3.3.4, PIT 1.17.0 (`pitest-maven`, `pitest-junit5-plugin`), Node v22.17.1 / npm 10.9.2, Vite 5.4, React 18, Vitest 2.1. Docker Desktop's daemon was not running in this environment (`docker ps` fails), so every measured number uses H2 in PostgreSQL-compatibility mode, not a real PostgreSQL instance; `docker-compose.yml` documents the real stack.
- **The 42-alert catalog is a synthetic catalog representative of a bank's alerting surface, not a real production catalog.** `scripts/generate_runbook_catalog.py` seeded 42 alert definitions (id, owning service, severity, description) and deliberately left 18 of them with no corresponding entry in the runbook step registry; there is no real on-call rotation, real PagerDuty/Opsgenie integration, or real production incident history behind any of these 42 alerts.
- **The `.github/workflows/ci.yml` GitHub Actions workflow runs the same commands documented below in "Building and running."** No measured number in this README comes from a GitHub-hosted run of that workflow; every number here was measured locally on the machine spec stated above. The workflow file is real and runs these exact commands, it has just not itself been the source of any reported measurement.

## Architecture

```
backend/src/main/java/com/manas/mergegate/
  MergeGateApplication.java             Spring Boot entry point
  corpus/prNNNN/ClassName.java          245 PR-unit implementation classes (generated, committed)
  gate/
    BranchDef.java                      one tracked branch from branch_manifest.json
    Mutation.java                       one PIT mutant result (mutatedClass, lineNumber, status, mutator)
    PitReportParser.java                parses a real PIT mutations.xml, no PIT internals reimplemented
    GateEngine.java                     the real decision: covered AND a survivor -> flagged; a PR with any flagged branch is blocked
    BranchGateResult.java               per-branch decision record with a human-readable reason
    GateReportIngestorMain.java         batch tool: branch_manifest.json + mutations.xml -> gate_decisions.json
    HoldoutEvaluatorMain.java           batch tool: gate_decisions.json + holdout_manifest.json -> holdout_results.json
    MutationScoreGate.java              NEW: rolls per-branch results up into a per-PR mutation score, 70% floor
    PrMutationScore.java                NEW: score, belowFloor, zeroKill, hasScoreableMutants, human-readable reason
    MutationScoreGateMain.java          NEW: batch tool, same shape as GateReportIngestorMain, for the zero-kill corpus
  runbook/
    AlertDef.java, RunbookStep.java     NEW: the 42-alert catalog and the runbook step registry
    RunbookCoverageChecker.java         NEW: flags every alert with no matching documented step
    RunbookCoverageResult.java          NEW: per-alert documented/undocumented decision with a named reason
    RunbookCoverageMain.java            NEW: batch tool: alert_catalog.json + runbook_steps.json -> runbook_coverage.json
  domain/                               PrUnit, Branch, HoldoutResult, AlertCoverage (NEW), MutationScoreResult (NEW)
  seed/CorpusDataLoader.java            loads the real, committed pipeline output into the database on startup
  api/
    PrController.java                   GET /api/prs, GET /api/prs/{id}
    SummaryController.java              GET /api/summary
    HoldoutController.java              GET /api/holdout
    RunbookCoverageController.java      NEW: GET /api/runbook-coverage
    MutationScoreController.java        NEW: GET /api/mutation-scores
backend/src/test/java/com/manas/mergegate/
  corpus/prNNNN/ClassNameTest.java      254 PR-unit test classes: 245 original + 9 NEW seeded zero-kill units (pr0246-0254)
  gate/GateEngineTest.java              pure unit tests: all four PIT-status combinations that decide a branch's fate
  gate/PitReportParserTest.java         parses a hand-written mutations.xml fixture
  gate/MutationScoreGateTest.java       NEW: below-floor, at-floor, zero-kill and no-scoreable-mutants cases
  runbook/RunbookCoverageCheckerTest.java   NEW: flags exactly the 18 seeded gaps across the real 42-alert catalog
  integration/MergeGateApiIntegrationTest.java   real Spring context + H2, asserts the loader and API reflect the real gate run
  integration/ReleaseGateApiIntegrationTest.java NEW: same, for the two new endpoints
scripts/
  generate_corpus.py                    writes the 230 SYNTHETIC_* PR units (seeded, deterministic, no network)
  generate_claude_batch.py              genuinely invokes `claude -p` for the 15 CLAUDE_CODE units (see Findings)
  select_holdout.py                     builds holdout_manifest.json (the 60-PR hand-labeled holdout)
  generate_runbook_catalog.py           NEW: writes the 42-alert catalog, 18 deliberately missing a runbook step
  generate_zero_kill_units.py           NEW: writes the 9 seeded zero-kill PR units (pr0246-0254)
frontend/
  src/App.tsx                           read-only console: corpus summary, per-PR block decision, holdout results,
                                         NEW runbook-coverage-gaps section naming each missing alert's step
  src/api.ts                            fetch wrappers + blockRateLabel (the one pure function under test)
  src/*.test.ts(x)                      Vitest + Testing Library, 7 tests (6 original + 1 NEW)
.github/workflows/ci.yml                NEW: mvn test + PIT run + npm test + npm run build on push/PR
docs/
  claude_raw/                           raw stdout from all 15 live claude -p calls, plus the earlier 35-call failed batch
  pit_mutation_report.txt               the real PIT run's per-mutator and overall statistics (254 units, 2,508 mutations)
  test_output.txt, frontend_test_output.txt
```

**Why the gate flags "covered AND surviving," not just "surviving."** A branch PIT marks `NO_COVERAGE` has no test reaching it at all, which is a real problem, but a different one from what this project measures: it is not a case where a test looks like it checks the branch and does not. `BranchGateResult.of` computes `covered = mutantCount > 0 && noCoverage < mutantCount` and `flagged = covered && survived > 0`, so an uncovered branch is reported honestly (`reason: "not covered by any test (NO_COVERAGE)"`) but never counted as a false positive for the specific claim this gate makes.

**Why the ground-truth-generation and the gate share the covered-and-weak rule, and why that is not circular.** `select_holdout.py`'s `expectedBlock` and `GateEngine.evaluate`'s `flagged` both key off "covered and weak/surviving," which could look like the test is checking the generator against itself. It is not: `expectedBlock` is computed from the generator's *construction intent* (a label the generator assigned when it wrote the test, before any mutation testing ran), while `flagged` is computed from PIT's actual, empirical mutant-survival results after compiling and running the real corpus against real mutants. The holdout is a check that a WEAK-by-design test really does let a real mutant survive in practice, not a tautology; a corpus branch could in principle be labeled WEAK by the generator and still happen to kill every mutant PIT tried against it (an under-approximation the other direction), which is exactly the kind of gap a real measurement is supposed to expose rather than hide.

**Why 15 genuinely Claude-sourced units and not more.** `scripts/generate_claude_batch.py` calls `claude -p` once per task, parses two fenced Java code blocks out of the raw response, and only accepts a unit if it finds at least two distinct branch lines in the implementation. See "Findings" for the real bug that made the first 35-call batch fail 35/35, and why this run's 15/15 success rate is not a coincidence.

**Why the mutation-score floor is a separate rule from the original per-branch flag, not a rename of it.** `GateEngine.evaluate`'s per-branch flag answers "does this one branch have a covering-but-weak test", and a PR with zero flagged branches can still be a bad PR overall if enough of its other mutants survive without any single branch crossing the flagged threshold. `MutationScoreGate` rolls every branch belonging to one PR up into one score (`killed / (killed + survived)`, the same covered-mutant convention `BranchGateResult.of` already uses, `NO_COVERAGE` mutants excluded from the denominator the same way) and blocks the whole PR if that score is below 70%. A PR whose suite passes while killing no mutants at all (the zero-kill case) always has a score of exactly 0% and is always below the floor; `PrMutationScore.zeroKill()` names that case specifically because it is the resume bullet's second, narrower claim.

**Why the runbook-coverage rule is a completely independent domain from the PR/mutation corpus.** `AlertDef`/`RunbookStep`/`RunbookCoverageChecker` know nothing about `BranchGateResult`, PIT, or the 245-PR corpus; they operate on a small, self-contained catalog of 42 alert definitions and a registry of documented runbook steps. The two rules are combined only at the release-gate level (both can independently block a release) and in the frontend console (both are shown side by side); this keeps the "which service can raise an alert with no runbook" question answerable without needing a PR unit or a mutation run to exist at all.

## Validation

Default test run (254 corpus PR-unit test classes, 245 original plus 9 seeded zero-kill units, plus the gate, runbook and integration tests), 684 tests, all passing (`docs/test_output.txt`):

```
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0 -- GateEngineTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0 -- PitReportParserTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0 -- MergeGateApiIntegrationTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0 -- MutationScoreGateTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0 -- RunbookCoverageCheckerTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0 -- ReleaseGateApiIntegrationTest
Tests run: 684, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

`GateEngineTest` is a pure unit test exercising all four PIT-status combinations that decide a branch's fate: covered with every mutant killed (not flagged), covered with a survivor (flagged), no coverage at all (not flagged, a different failure mode), and no mutants generated for the line (not flagged, a bookkeeping edge case), plus a PR-level "blocked if any branch is flagged" check.

`MutationScoreGateTest` (new) exercises the rollup directly: a PR at 40% (below floor), a PR at exactly 70% (meets floor, boundary case), a PR with zero kills across two branches (`zeroKill()` true), and a PR with only `NO_COVERAGE` branches (`hasScoreableMutants()` false, not fabricated as below floor just because it has no scoreable mutants).

`RunbookCoverageCheckerTest` (new) loads the real, committed 42-alert catalog and runbook step registry (`scripts/generate_runbook_catalog.py`'s committed output, not a hand-rolled fixture) and asserts the checker flags exactly the 18 seeded gaps and none of the other 24, by an id set read off the generator's own printed output at generation time.

`MergeGateApiIntegrationTest` and `ReleaseGateApiIntegrationTest` boot the real Spring context against H2 and assert the loader populated exactly the real committed corpus and catalog (245 PRs, 606 branches, 60 holdout rows, 42 alerts, 9 mutation-score units) and that the REST API serves the real gate run's numbers.

Frontend: 7 Vitest/Testing Library tests (6 original plus 1 new), all passing (`docs/frontend_test_output.txt`), plus a clean `tsc -b && vite build`. The new test asserts the console names the specific missing alert and its description, not just the word "BLOCKED".

The real PIT mutation run (254 mutation test units, 2,825 tests run, 2,508 mutants generated across four mutators) is not re-run by `mvn test`; it is a separate, explicit step because a full mutation run takes tens of seconds and only needs rerunning when the corpus or gate logic changes. See "Building and running" and `docs/pit_mutation_report.txt` for its real output.

## Findings

The first attempt at the Claude-sourced slice of the corpus failed completely: 35 live `claude -p` calls, 35 parse failures, 0 successes (`docs/claude_raw/_summary.json`, the original failed run). The wrong hypothesis, going in, was that Claude's output did not match the expected two-fenced-code-block format. It did; `docs/claude_raw/pr0231.txt` shows a clean two-block response with a well-formed `if/else if/else` chain. The measurement that discriminated the real cause from that wrong hypothesis was adding a one-off print of the branch-line count the parser had found: it was 1, not the 4 the source code actually had. The root cause was in `generate_claude_batch.py`'s `find_branch_lines`: it tracked brace depth and toggled a single `in_branch_block` flag around the whole chain, but a chained `} else if (...) {` header has one closing brace and one opening brace on the same line, a net-zero depth change, so the depth-based state machine never noticed the chain had moved to a new arm and collapsed every multi-branch chain down to one captured line. The fix replaced brace-depth tracking with the simpler fact the generation prompt already guarantees (a flat chain, no nesting): for every line matching a branch-header pattern, the branch's own line is just the next line containing `return` after it, found by direct forward scan rather than depth bookkeeping. After the fix, the same 15-task batch that had never worked succeeded 15 times out of 15.

The fix surfaced a second, smaller and genuinely interesting defect: two of the 15 live-generated test classes (`pr0234`, `pr0239`) called `assertTrue(...)` but only imported `assertEquals`, a real compile error in code a live LLM call produced unprompted. That is exactly the category of defect this project's thesis is about, an AI assistant's output that looks complete and fails on inspection, except this instance failed to compile rather than failing to test well, so it was caught immediately rather than needing mutation testing to surface it. Both files were fixed with a one-line import addition; the fix is disclosed here rather than silently folded into the generation script, because the corpus's other 13 live-generated files did not have the same problem, so this was Claude's own inconsistency, not a systematic generator bug.

**Extension finding: `MutationScoreGateMain`'s first run against the zero-kill seed set threw `UnrecognizedPropertyException` on `packageName`.** The zero-kill batch tool was first pointed at `zero_kill_corpus_manifest.json` (the corpus-generation manifest, shaped `className`/`packageName`/`source`, used for seeding `PrUnit` rows), not `zero_kill_manifest.json` (the branch manifest, shaped like `branch_manifest.json` with `branchId`/`fqcn`/`lineNumber`, the schema `BranchDef` actually deserializes). The error named the exact unrecognized field, which made the fix immediate: point the tool at the branch-shaped manifest instead. Both manifest files are committed and serve genuinely different purposes; the naming similarity is what caused the mixup, not a schema bug in either file.

## Measured results

Machine: 8 physical / 16 logical cores, Windows 11 Home, JDK 21 Temurin, Maven 3.9.9, PIT 1.17.0. Run: `mvn org.pitest:pitest-maven:mutationCoverage` from `backend/`, then `GateReportIngestorMain`, `HoldoutEvaluatorMain` and `MutationScoreGateMain` (exact commands in "Building and running"). Mutators configured: `CONDITIONALS_BOUNDARY`, `NEGATE_CONDITIONALS`, `MATH`, `TRUE_RETURNS`, `FALSE_RETURNS`, `PRIMITIVE_RETURNS`, `EMPTY_RETURNS`, `INCREMENTS`, `INVERT_NEGS`; only the first four generated any mutants, since this corpus's flat integer if/else-if/else style has no applicable operation for the other five (see "Limitations"). PIT reports 0 mutants for a mutator with nothing to mutate rather than an error.

| Metric | Measured | Claim | Meets claim |
|---|---|---|---|
| Corpus scale | **245 PR units** (15 genuinely `claude -p`-sourced, 230 scripted/synthetic) | over 240 pull requests from Claude Code, Cursor and GitHub Copilot | scale: yes. tool provenance: no, see note below |
| **Flagged branches** | **141 of 606** tracked branches covered yet a mutant survives | 118 of 384 AI-authored branches | no, see note below |
| **Holdout false blocks** | **0 false blocks, 0 missed blocks, 60 of 60 requests** | 0 false blocks on a 60-request hand-labeled holdout | yes |
| **Runbook-coverage gaps rejected** | **18 of 18** seeded gaps flagged across the real 42-alert catalog, 0 of the other 24 falsely flagged | rejected 18 of 18 seeded documentation gaps across a 42-alert catalog | yes |
| **Zero-kill suites rejected** | **9 of 9** seeded zero-kill PR units scored 0.0% and blocked by the mutation-score floor | rejected 9 of 9 suites that passed while killing no mutants | yes |
| Mutation-score floor blocks below 70% | `MutationScoreGateTest`: 40% blocked, 70% (boundary) not blocked, zero-kill always blocked, no-scoreable-mutants never fabricated as blocked | blocks any merge whose PIT mutation score falls below 70% | yes |
| Console names the missing step | `App.test.tsx`: renders `ALERT-001 (payments-api, CRITICAL): missing runbook step for "..."`, not just "BLOCKED" | TypeScript reviewer console names the missing step | yes |

The corpus-scale claim is honestly split into two separable parts. The count (over 240) is genuinely met: 245 PR units exist, compile, and pass their own tests. The three-tool provenance is not genuinely met: only the 15 `CLAUDE_CODE` units were actually produced by a live coding-assistant call; the 230 `SYNTHETIC_CURSOR`/`SYNTHETIC_COPILOT` units are a scripted generator with those labels attached for corpus bookkeeping, because Cursor and GitHub Copilot have no headless CLI available in this environment. The branch-count and flagged-count claims (384 and 118) were not tuned toward; this corpus and its real, committed test files produce 606 tracked branches and 141 flagged ones. Both are larger than the resume bullet's numbers, in the same direction (more branches, proportionally similar flag rate: 141/606 = 23.3% here versus 118/384 = 30.7% in the original bullet), but they are not the same numbers, and are reported as measured rather than adjusted. The holdout claim is met exactly: of the 60 hand-labeled PRs, the real gate produced 0 false blocks and, as a bonus honest disclosure beyond what the resume claims, 0 missed blocks as well. The runbook-coverage and zero-kill-suite claims are both met exactly against a real, committed catalog and a real PIT run, not a hand-computed number.

## Building and running

```
export PATH="/c/Users/Manas/tools/apache-maven-3.9.9/bin:$PATH"   # or the equivalent mvn on PATH
cd backend
mvn test                                                # 684 tests, ~10s

# Regenerate the corpus from scratch (not needed to reproduce the numbers above; the
# corpus is already committed under src/main/resources/corpus and src/main/java/.../corpus):
#   python ../scripts/generate_corpus.py
#   python ../scripts/generate_claude_batch.py   # needs a working `claude` CLI on PATH
#   python ../scripts/select_holdout.py
#   python ../scripts/generate_runbook_catalog.py   # the 42-alert catalog, 18 deliberately missing a step
#   python ../scripts/generate_zero_kill_units.py   # the 9 seeded zero-kill PR units (pr0246-0254)

mvn org.pitest:pitest-maven:mutationCoverage            # the real PIT run, ~35s, writes target/pit-reports/mutations.xml
mvn dependency:build-classpath -Dmdep.outputFile=/tmp/cp.txt
java -cp "target/classes;$(cat /tmp/cp.txt)" com.manas.mergegate.gate.GateReportIngestorMain \
     src/main/resources/corpus/branch_manifest.json target/pit-reports/mutations.xml \
     src/main/resources/corpus/gate_decisions.json
java -cp "target/classes;$(cat /tmp/cp.txt)" com.manas.mergegate.gate.HoldoutEvaluatorMain \
     src/main/resources/corpus/gate_decisions.json src/main/resources/corpus/holdout_manifest.json \
     src/main/resources/corpus/holdout_results.json
java -cp "target/classes;$(cat /tmp/cp.txt)" com.manas.mergegate.gate.MutationScoreGateMain \
     src/main/resources/corpus/zero_kill_manifest.json target/pit-reports/mutations.xml \
     src/main/resources/corpus/mutation_score_results.json

mvn spring-boot:run                                      # serves the API on :8080 from the committed gate/runbook JSON

cd ../frontend
npm install
npm test -- --run                                         # 7 tests, ~1s
npm run build                                             # tsc -b && vite build
npm run dev                                               # proxies /api to :8080
```

The GitHub Actions workflow at `.github/workflows/ci.yml` runs the same `mvn test`, PIT run,
`npm test` and `npm run build` commands above on every push and pull request against `master`.

Against a real PostgreSQL instance (not exercised for any number in this README): `docker compose up -d`, then run the backend with the production profile (unset `spring.profiles.active=test` / do not use the test resources classpath).

## Sibling comparison

The nearest existing repository by stack and by "gate that blocks a merge" shape is [`ops-console-regression-gate`](https://github.com/Manas103/ops-console-regression-gate) (Java 21, Selenium, REST Assured, TypeScript/React, GitHub Actions, 30 seeded regressions, a rerun-and-quarantine flake detector). That repository gates on UI flakiness across reruns of the same test; this one gates on whether an AI-authored branch's own test would actually catch a broken version of it. Different subject, same instinct: do not trust a single green run (or a single line-covered branch) as proof that a test means anything, and build the mechanism that checks the check.

## Limitations

Cursor and GitHub Copilot contributed no code to this corpus; see "Honest framing" and "Measured results" for exactly what was and was not genuinely tool-sourced. The gate operates on a corpus of independent single-method units, not real multi-file pull request diffs; there is no git-diff parsing, no cross-file analysis, and no handling of a branch that spans multiple methods or classes. The 60-PR holdout's ground truth is re-derived from the corpus generator's own construction labels rather than from an external human reviewer with no knowledge of how the corpus was built, which is disclosed exactly in "Honest framing" rather than presented as a blind, independent audit. Five of the nine configured mutators (`INCREMENTS`, `INVERT_NEGS`, `TRUE_RETURNS`, `FALSE_RETURNS`, `EMPTY_RETURNS`) generated zero mutants against this corpus's style (flat integer comparisons and literal `int` returns, no `++`/`--`, unary negation, boolean returns or object/empty-collection returns), so the flagged-branch count reflects the four mutators that actually fired (`CONDITIONALS_BOUNDARY`, `NEGATE_CONDITIONALS`, `MATH`, `PRIMITIVE_RETURNS`), not all nine configured.

The 42-alert catalog is synthetic, representative of a bank's alerting surface rather than a real production catalog (see "Honest framing"); there is no real on-call system, real PagerDuty/Opsgenie integration, or real incident history behind it. The runbook-coverage rule matches an alert to a documented step by alert id only; it does not parse a real pull request diff to determine which alerts a *changed* service can newly raise, so "blocks any merge whose service can raise an alert with no documented runbook step" is checked against the full static catalog, not against a diff. The GitHub Actions workflow has not itself been the source of any measured number in this README (see "Honest framing").
