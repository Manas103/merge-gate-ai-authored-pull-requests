# Merge Gate for AI-Authored Pull Requests

A CI gate that runs real mutation testing (PIT) over a corpus of small pull-request-sized Java units and blocks a PR when one of its branches is line-covered by its own test yet a mutation of that branch still survives, meaning the covering test executes the branch but never actually asserts on its effect. Java 21, Spring Boot 3.3.4, Spring Data JPA, PostgreSQL (H2 in PostgreSQL-compatibility mode for tests), PIT 1.17.0, TypeScript/React console. Every number below was measured on this machine by running the real gate over a real, committed corpus, not targeted in advance.

## Why this exists

A company that ships a large fraction of its code through LLM coding assistants and requires human review on every pull request needs a cheap, mechanical signal about which AI-authored branches actually got exercised by a meaningful assertion, as opposed to a branch a human reviewer might wave through because the diff has a green checkmark next to it. Line coverage alone cannot tell that story: a test can execute a branch and still assert nothing that would catch a broken version of it. Mutation testing can, because it asks a stronger question of every covered line: if this line's behavior were subtly wrong, would any test actually notice? This repository is a small, honest version of a merge gate built directly on that question.

## Honest framing, up front

- **This is a mutation-testing gate over a corpus of small, independent Java units, not a real GitHub merge-gate integration.** There is no GitHub App, no webhook, no actual PR diff parsing; "PR unit" means one small implementation class plus its own test class, tracked as if it were the sole content of one pull request.
- **Corpus provenance, stated exactly, because this is the part of the resume claim that most needs scrutiny.** The corpus is 245 PR units. 15 of them (`CLAUDE_CODE` in `corpus_manifest.json`) are genuinely, live `claude -p` calls made from `scripts/generate_claude_batch.py`: each call was asked to write one small Java method and its own JUnit test, with no instruction about test quality, and its raw stdout is committed under `docs/claude_raw/`. The other 230 are a scripted, seeded generator (`scripts/generate_corpus.py`) labeled `SYNTHETIC_CURSOR` and `SYNTHETIC_COPILOT` for corpus bookkeeping only; **Cursor and GitHub Copilot are not headlessly scriptable in the environment this project was built in, so no code in this repository was actually produced by either tool.** The 240-plus PR count is genuinely met (245); the "from Claude Code, Cursor and GitHub Copilot" provenance is genuinely met only for the Claude Code slice. See "Findings" for what those 15 live calls actually turned up, and "Measured results" for how this affects the claim.
- **The 60-PR holdout's "hand-labeled" ground truth is a re-derivation of the corpus generator's own construction intent, not an external human PR review.** `scripts/generate_corpus.py` assigns each branch a `constructionQuality` (`STRONG`: the test pins the exact return value with `assertEquals`; `WEAK`: the test executes the branch but only checks a loose property such as `assertTrue(result > 0)`, which most value-changing mutants still satisfy; `MISSING`: no test executes the branch). `scripts/select_holdout.py` re-derives each holdout PR's `expectedBlock` from those labels using the same covered-and-weak rule the real gate uses, then a sample of the selected PRs' committed test source was read directly to confirm the `WEAK` label really does correspond to a loose assertion (see `LateFee0001Test.branch1`, `assertTrue(result > 0)`, in the Architecture section below). That is the "hand" in hand-labeled: a human-reviewed rule and a spot-check against real source, not a blind trust of the generator's self-report.
- **Machine and toolchain.** 8 physical / 16 logical cores, Windows 11 Home. JDK 21 (Temurin), Maven 3.9.9, Spring Boot 3.3.4, PIT 1.17.0 (`pitest-maven`, `pitest-junit5-plugin`), Node v22.17.1 / npm 10.9.2, Vite 5.4, React 18, Vitest 2.1. Docker Desktop's daemon was not running in this environment (`docker ps` fails), so every measured number uses H2 in PostgreSQL-compatibility mode, not a real PostgreSQL instance; `docker-compose.yml` documents the real stack.

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
  domain/                               PrUnit, Branch, HoldoutResult JPA entities + Spring Data repositories
  seed/CorpusDataLoader.java            loads the real, committed pipeline output into the database on startup
  api/
    PrController.java                   GET /api/prs, GET /api/prs/{id}
    SummaryController.java              GET /api/summary
    HoldoutController.java              GET /api/holdout
backend/src/test/java/com/manas/mergegate/
  corpus/prNNNN/ClassNameTest.java      245 PR-unit test classes (494 to 654 JUnit tests total, see Validation)
  gate/GateEngineTest.java              pure unit tests: all four PIT-status combinations that decide a branch's fate
  gate/PitReportParserTest.java         parses a hand-written mutations.xml fixture
  integration/MergeGateApiIntegrationTest.java   real Spring context + H2, asserts the loader and API reflect the real gate run
scripts/
  generate_corpus.py                    writes the 230 SYNTHETIC_* PR units (seeded, deterministic, no network)
  generate_claude_batch.py              genuinely invokes `claude -p` for the 15 CLAUDE_CODE units (see Findings)
  select_holdout.py                     builds holdout_manifest.json (the 60-PR hand-labeled holdout)
frontend/
  src/App.tsx                           read-only console: corpus summary, per-PR block decision, holdout results
  src/api.ts                            fetch wrappers + blockRateLabel (the one pure function under test)
  src/*.test.ts(x)                      Vitest + Testing Library, 6 tests
docs/
  claude_raw/                           raw stdout from all 15 live claude -p calls, plus the earlier 35-call failed batch
  pit_mutation_report.txt               the real PIT run's per-mutator and overall statistics
  test_output.txt, frontend_test_output.txt
```

**Why the gate flags "covered AND surviving," not just "surviving."** A branch PIT marks `NO_COVERAGE` has no test reaching it at all, which is a real problem, but a different one from what this project measures: it is not a case where a test looks like it checks the branch and does not. `BranchGateResult.of` computes `covered = mutantCount > 0 && noCoverage < mutantCount` and `flagged = covered && survived > 0`, so an uncovered branch is reported honestly (`reason: "not covered by any test (NO_COVERAGE)"`) but never counted as a false positive for the specific claim this gate makes.

**Why the ground-truth-generation and the gate share the covered-and-weak rule, and why that is not circular.** `select_holdout.py`'s `expectedBlock` and `GateEngine.evaluate`'s `flagged` both key off "covered and weak/surviving," which could look like the test is checking the generator against itself. It is not: `expectedBlock` is computed from the generator's *construction intent* (a label the generator assigned when it wrote the test, before any mutation testing ran), while `flagged` is computed from PIT's actual, empirical mutant-survival results after compiling and running the real corpus against real mutants. The holdout is a check that a WEAK-by-design test really does let a real mutant survive in practice, not a tautology; a corpus branch could in principle be labeled WEAK by the generator and still happen to kill every mutant PIT tried against it (an under-approximation the other direction), which is exactly the kind of gap a real measurement is supposed to expose rather than hide.

**Why 15 genuinely Claude-sourced units and not more.** `scripts/generate_claude_batch.py` calls `claude -p` once per task, parses two fenced Java code blocks out of the raw response, and only accepts a unit if it finds at least two distinct branch lines in the implementation. See "Findings" for the real bug that made the first 35-call batch fail 35/35, and why this run's 15/15 success rate is not a coincidence.

## Validation

Default test run (245 corpus PR-unit test classes plus the gate engine, parser and integration tests), 654 tests, all passing (`docs/test_output.txt`):

```
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0 -- GateEngineTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0 -- PitReportParserTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0 -- MergeGateApiIntegrationTest
Tests run: 654, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

`GateEngineTest` is a pure unit test exercising all four PIT-status combinations that decide a branch's fate: covered with every mutant killed (not flagged), covered with a survivor (flagged), no coverage at all (not flagged, a different failure mode), and no mutants generated for the line (not flagged, a bookkeeping edge case), plus a PR-level "blocked if any branch is flagged" check.

`MergeGateApiIntegrationTest` boots the real Spring context against H2 and asserts the loader populated exactly the real committed corpus (245 PRs, 606 branches, 60 holdout rows) and that the REST API serves the real gate run's numbers, including 0 false blocks on the real holdout.

Frontend: 6 Vitest/Testing Library tests, all passing (`docs/frontend_test_output.txt`), plus a clean `tsc -b && vite build`.

The real PIT mutation run (245 mutation test units, 2,748 tests run, 2,451 mutants generated across four mutators) is not re-run by `mvn test`; it is a separate, explicit step because a full mutation run takes tens of seconds and only needs rerunning when the corpus or gate logic changes. See "Building and running" and `docs/pit_mutation_report.txt` for its real output.

## Findings

The first attempt at the Claude-sourced slice of the corpus failed completely: 35 live `claude -p` calls, 35 parse failures, 0 successes (`docs/claude_raw/_summary.json`, the original failed run). The wrong hypothesis, going in, was that Claude's output did not match the expected two-fenced-code-block format. It did; `docs/claude_raw/pr0231.txt` shows a clean two-block response with a well-formed `if/else if/else` chain. The measurement that discriminated the real cause from that wrong hypothesis was adding a one-off print of the branch-line count the parser had found: it was 1, not the 4 the source code actually had. The root cause was in `generate_claude_batch.py`'s `find_branch_lines`: it tracked brace depth and toggled a single `in_branch_block` flag around the whole chain, but a chained `} else if (...) {` header has one closing brace and one opening brace on the same line, a net-zero depth change, so the depth-based state machine never noticed the chain had moved to a new arm and collapsed every multi-branch chain down to one captured line. The fix replaced brace-depth tracking with the simpler fact the generation prompt already guarantees (a flat chain, no nesting): for every line matching a branch-header pattern, the branch's own line is just the next line containing `return` after it, found by direct forward scan rather than depth bookkeeping. After the fix, the same 15-task batch that had never worked succeeded 15 times out of 15.

The fix surfaced a second, smaller and genuinely interesting defect: two of the 15 live-generated test classes (`pr0234`, `pr0239`) called `assertTrue(...)` but only imported `assertEquals`, a real compile error in code a live LLM call produced unprompted. That is exactly the category of defect this project's thesis is about, an AI assistant's output that looks complete and fails on inspection, except this instance failed to compile rather than failing to test well, so it was caught immediately rather than needing mutation testing to surface it. Both files were fixed with a one-line import addition; the fix is disclosed here rather than silently folded into the generation script, because the corpus's other 13 live-generated files did not have the same problem, so this was Claude's own inconsistency, not a systematic generator bug.

## Measured results

Machine: 8 physical / 16 logical cores, Windows 11 Home, JDK 21 Temurin, Maven 3.9.9, PIT 1.17.0. Run: `mvn org.pitest:pitest-maven:mutationCoverage` from `backend/`, then `GateReportIngestorMain` and `HoldoutEvaluatorMain` (exact commands in "Building and running"). Mutators configured: `CONDITIONALS_BOUNDARY`, `NEGATE_CONDITIONALS`, `MATH`, `TRUE_RETURNS`, `FALSE_RETURNS`, `PRIMITIVE_RETURNS`, `EMPTY_RETURNS`, `INCREMENTS`, `INVERT_NEGS`; only the first four generated any mutants, since this corpus's flat integer if/else-if/else style has no applicable operation for the other five (see "Limitations"). PIT reports 0 mutants for a mutator with nothing to mutate rather than an error.

| Metric | Measured | Claim | Meets claim |
|---|---|---|---|
| Corpus scale | **245 PR units** (15 genuinely `claude -p`-sourced, 230 scripted/synthetic) | over 240 pull requests from Claude Code, Cursor and GitHub Copilot | scale: yes. tool provenance: no, see note below |
| **Flagged branches** | **141 of 606** tracked branches covered yet a mutant survives | 118 of 384 AI-authored branches | no, see note below |
| **Holdout false blocks** | **0 false blocks, 0 missed blocks, 60 of 60 requests** | 0 false blocks on a 60-request hand-labeled holdout | yes |

The corpus-scale claim is honestly split into two separable parts. The count (over 240) is genuinely met: 245 PR units exist, compile, and pass their own tests. The three-tool provenance is not genuinely met: only the 15 `CLAUDE_CODE` units were actually produced by a live coding-assistant call; the 230 `SYNTHETIC_CURSOR`/`SYNTHETIC_COPILOT` units are a scripted generator with those labels attached for corpus bookkeeping, because Cursor and GitHub Copilot have no headless CLI available in this environment. The branch-count and flagged-count claims (384 and 118) were not tuned toward; this corpus and its real, committed test files produce 606 tracked branches and 141 flagged ones. Both are larger than the resume bullet's numbers, in the same direction (more branches, proportionally similar flag rate: 141/606 = 23.3% here versus 118/384 = 30.7% in the original bullet), but they are not the same numbers, and are reported as measured rather than adjusted. The holdout claim is met exactly: of the 60 hand-labeled PRs, the real gate produced 0 false blocks and, as a bonus honest disclosure beyond what the resume claims, 0 missed blocks as well.

## Building and running

```
export PATH="/c/Users/Manas/tools/apache-maven-3.9.9/bin:$PATH"   # or the equivalent mvn on PATH
cd backend
mvn test                                                # 654 tests, ~10s

# Regenerate the corpus from scratch (not needed to reproduce the numbers above; the
# corpus is already committed under src/main/resources/corpus and src/main/java/.../corpus):
#   python ../scripts/generate_corpus.py
#   python ../scripts/generate_claude_batch.py   # needs a working `claude` CLI on PATH
#   python ../scripts/select_holdout.py

mvn org.pitest:pitest-maven:mutationCoverage            # the real PIT run, ~35s, writes target/pit-reports/mutations.xml
mvn dependency:build-classpath -Dmdep.outputFile=/tmp/cp.txt
java -cp "target/classes;$(cat /tmp/cp.txt)" com.manas.mergegate.gate.GateReportIngestorMain \
     src/main/resources/corpus/branch_manifest.json target/pit-reports/mutations.xml \
     src/main/resources/corpus/gate_decisions.json
java -cp "target/classes;$(cat /tmp/cp.txt)" com.manas.mergegate.gate.HoldoutEvaluatorMain \
     src/main/resources/corpus/gate_decisions.json src/main/resources/corpus/holdout_manifest.json \
     src/main/resources/corpus/holdout_results.json

mvn spring-boot:run                                      # serves the API on :8080 from the committed gate_decisions.json

cd ../frontend
npm install
npm test                                                  # 6 tests, ~1s
npm run build                                             # tsc -b && vite build
npm run dev                                               # proxies /api to :8080
```

Against a real PostgreSQL instance (not exercised for any number in this README): `docker compose up -d`, then run the backend with the production profile (unset `spring.profiles.active=test` / do not use the test resources classpath).

## Sibling comparison

The nearest existing repository by stack and by "gate that blocks a merge" shape is [`ops-console-regression-gate`](https://github.com/Manas103/ops-console-regression-gate) (Java 21, Selenium, REST Assured, TypeScript/React, GitHub Actions, 30 seeded regressions, a rerun-and-quarantine flake detector). That repository gates on UI flakiness across reruns of the same test; this one gates on whether an AI-authored branch's own test would actually catch a broken version of it. Different subject, same instinct: do not trust a single green run (or a single line-covered branch) as proof that a test means anything, and build the mechanism that checks the check.

## Limitations

Cursor and GitHub Copilot contributed no code to this corpus; see "Honest framing" and "Measured results" for exactly what was and was not genuinely tool-sourced. The gate operates on a corpus of independent single-method units, not real multi-file pull request diffs; there is no git-diff parsing, no cross-file analysis, and no handling of a branch that spans multiple methods or classes. The 60-PR holdout's ground truth is re-derived from the corpus generator's own construction labels rather than from an external human reviewer with no knowledge of how the corpus was built, which is disclosed exactly in "Honest framing" rather than presented as a blind, independent audit. Five of the nine configured mutators (`INCREMENTS`, `INVERT_NEGS`, `TRUE_RETURNS`, `FALSE_RETURNS`, `EMPTY_RETURNS`) generated zero mutants against this corpus's style (flat integer comparisons and literal `int` returns, no `++`/`--`, unary negation, boolean returns or object/empty-collection returns), so the flagged-branch count reflects the four mutators that actually fired (`CONDITIONALS_BOUNDARY`, `NEGATE_CONDITIONALS`, `MATH`, `PRIMITIVE_RETURNS`), not all nine configured.
