package com.manas.mergegate.gate;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pure unit tests for PrMutationScore/MutationScoreGate: hand-built
 * BranchGateResult fixtures (the same rollup GateEngine.evaluate produces
 * from a real PIT run) standing in for two engineered PR units, one scoring
 * below the 70% floor and one above it, plus the zero-kill and
 * no-scoreable-mutants edge cases.
 */
class MutationScoreGateTest {

    private BranchGateResult branch(String prId, String branchId, int killed, int survived, int noCoverage) {
        int mutantCount = killed + survived + noCoverage;
        boolean covered = mutantCount > 0 && noCoverage < mutantCount;
        boolean flagged = covered && survived > 0;
        return new BranchGateResult(prId, branchId, "com.example.Foo", 10, covered, flagged,
                mutantCount, killed, survived, noCoverage, "fixture");
    }

    @Test
    void aPrScoringBelowSeventyPercentIsBelowFloor() {
        // 2 killed, 3 survived across two branches -> 2/5 = 40%, below 70%.
        List<BranchGateResult> results = List.of(
                branch("pr9001", "pr9001-b0", 2, 1, 0),
                branch("pr9001", "pr9001-b1", 0, 2, 0));
        PrMutationScore score = PrMutationScore.of("pr9001", results);
        assertThat(score.hasScoreableMutants()).isTrue();
        assertThat(score.score()).isCloseTo(0.4, org.assertj.core.data.Offset.offset(0.0001));
        assertThat(score.belowFloor()).isTrue();
        assertThat(score.zeroKill()).isFalse();
        assertThat(score.reason()).contains("below the 70% floor");
    }

    @Test
    void aPrScoringAtOrAboveSeventyPercentIsNotBelowFloor() {
        // 7 killed, 3 survived -> 70%, meets the floor exactly.
        List<BranchGateResult> results = List.of(
                branch("pr9002", "pr9002-b0", 5, 1, 0),
                branch("pr9002", "pr9002-b1", 2, 2, 0));
        PrMutationScore score = PrMutationScore.of("pr9002", results);
        assertThat(score.score()).isCloseTo(0.7, org.assertj.core.data.Offset.offset(0.0001));
        assertThat(score.belowFloor()).isFalse();
        assertThat(score.reason()).contains("meets the 70% floor");
    }

    @Test
    void aSuiteThatPassesWhileKillingNoMutantsIsZeroKillAndBelowFloor() {
        List<BranchGateResult> results = List.of(
                branch("pr9003", "pr9003-b0", 0, 4, 0),
                branch("pr9003", "pr9003-b1", 0, 2, 0));
        PrMutationScore score = PrMutationScore.of("pr9003", results);
        assertThat(score.score()).isZero();
        assertThat(score.zeroKill()).isTrue();
        assertThat(score.belowFloor()).isTrue();
        assertThat(score.reason()).contains("killed no mutants");
    }

    @Test
    void aPrWithOnlyNoCoverageBranchesHasNoScoreableMutantsAndIsNotFabricatedAsBelowFloor() {
        List<BranchGateResult> results = List.of(branch("pr9004", "pr9004-b0", 0, 0, 3));
        PrMutationScore score = PrMutationScore.of("pr9004", results);
        assertThat(score.hasScoreableMutants()).isFalse();
        assertThat(score.belowFloor()).isFalse();
        assertThat(score.zeroKill()).isFalse();
        assertThat(score.reason()).contains("no scoreable mutants");
    }

    @Test
    void scoreAllRollsUpMultiplePrsAndIsBlockedByMutationScoreFloorFindsOnlyTheFailingOne() {
        List<BranchGateResult> results = List.of(
                branch("pr9005", "pr9005-b0", 1, 4, 0),   // 20%, below floor
                branch("pr9006", "pr9006-b0", 9, 1, 0));  // 90%, above floor
        MutationScoreGate gate = new MutationScoreGate();
        List<PrMutationScore> scores = gate.scoreAll(results);
        assertThat(scores).hasSize(2);
        assertThat(gate.isBlockedByMutationScoreFloor("pr9005", scores)).isTrue();
        assertThat(gate.isBlockedByMutationScoreFloor("pr9006", scores)).isFalse();
    }
}
