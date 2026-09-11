package com.manas.mergegate.gate;

import java.util.List;

/**
 * One PR unit's overall mutation score, rolled up from its BranchGateResult
 * rows: killed / (killed + survived). This matches BranchGateResult.of's own
 * convention for what counts as "coverage": NO_COVERAGE mutants are a
 * different failure mode (no test reaches the line at all) and are excluded
 * from the score's denominator rather than counted as failed kills, so a PR
 * with entirely uncovered branches is reported as "no scoreable mutants"
 * rather than fabricating a 0% floor violation for a problem this rule does
 * not claim to measure.
 */
public record PrMutationScore(
        String prId,
        int killedCount,
        int survivedCount,
        int noCoverageCount,
        boolean hasScoreableMutants,
        double score,
        boolean belowFloor,
        boolean zeroKill,
        String reason) {

    public static final double FLOOR_SCORE = 0.70;

    public static PrMutationScore of(String prId, List<BranchGateResult> branchResults) {
        int killed = branchResults.stream().mapToInt(BranchGateResult::killedCount).sum();
        int survived = branchResults.stream().mapToInt(BranchGateResult::survivedCount).sum();
        int noCoverage = branchResults.stream().mapToInt(BranchGateResult::noCoverageCount).sum();
        int scored = killed + survived;
        boolean hasScoreable = scored > 0;
        double score = hasScoreable ? (double) killed / scored : 0.0;
        boolean belowFloor = hasScoreable && score < FLOOR_SCORE;
        boolean zeroKill = hasScoreable && killed == 0;

        String reason;
        if (!hasScoreable) {
            reason = "no scoreable mutants (all tracked branches are NO_COVERAGE or generated no mutants)";
        } else if (zeroKill) {
            reason = String.format(
                    "mutation score 0.0%% (%d of %d scoreable mutants killed): test suite passed but killed no mutants",
                    killed, scored);
        } else if (belowFloor) {
            reason = String.format(
                    "mutation score %.1f%% (%d of %d scoreable mutants killed) is below the 70%% floor",
                    score * 100.0, killed, scored);
        } else {
            reason = String.format(
                    "mutation score %.1f%% (%d of %d scoreable mutants killed) meets the 70%% floor",
                    score * 100.0, killed, scored);
        }
        return new PrMutationScore(prId, killed, survived, noCoverage, hasScoreable, score, belowFloor, zeroKill, reason);
    }
}
