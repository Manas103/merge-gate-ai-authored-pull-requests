package com.manas.mergegate.gate;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pure unit tests for {@link GateEngine}: no database, no real PIT run, hand-built
 * {@link Mutation} fixtures standing in for what {@link PitReportParser} would have parsed
 * out of a real {@code mutations.xml}. Exercises exactly the four PIT-status combinations
 * that decide a branch's fate: covered and all killed (not flagged), covered with a
 * survivor (flagged, the whole point of the gate), no coverage at all (not flagged, since
 * an uncovered branch cannot be "covered yet surviving"), and no mutants generated for the
 * line at all (not flagged, a bookkeeping edge case).
 */
class GateEngineTest {

    private static final BranchDef DEF = new BranchDef(
            "pr0001", "pr0001-b0", "Foo", "com.example.Foo", 10, "test branch", "STRONG");

    @Test
    void coveredWithAllMutantsKilledIsNotFlagged() {
        List<Mutation> mutations = List.of(
                new Mutation("com.example.Foo", 10, "KILLED", "MATH"),
                new Mutation("com.example.Foo", 10, "KILLED", "CONDITIONALS_BOUNDARY"));
        List<BranchGateResult> results = new GateEngine().evaluate(List.of(DEF), mutations);
        assertThat(results).hasSize(1);
        BranchGateResult r = results.get(0);
        assertThat(r.covered()).isTrue();
        assertThat(r.flagged()).isFalse();
        assertThat(r.killedCount()).isEqualTo(2);
        assertThat(r.survivedCount()).isZero();
    }

    @Test
    void coveredWithASurvivorIsFlagged() {
        List<Mutation> mutations = List.of(
                new Mutation("com.example.Foo", 10, "KILLED", "MATH"),
                new Mutation("com.example.Foo", 10, "SURVIVED", "RETURN_VALUES"));
        List<BranchGateResult> results = new GateEngine().evaluate(List.of(DEF), mutations);
        BranchGateResult r = results.get(0);
        assertThat(r.covered()).isTrue();
        assertThat(r.flagged()).isTrue();
        assertThat(r.survivedCount()).isEqualTo(1);
        assertThat(r.reason()).contains("1 of 2 mutant(s)");
    }

    @Test
    void noCoverageIsNotFlaggedEvenThoughUncheckedIsWorse() {
        List<Mutation> mutations = List.of(
                new Mutation("com.example.Foo", 10, "NO_COVERAGE", "MATH"));
        List<BranchGateResult> results = new GateEngine().evaluate(List.of(DEF), mutations);
        BranchGateResult r = results.get(0);
        assertThat(r.covered()).isFalse();
        assertThat(r.flagged()).isFalse();
        assertThat(r.reason()).contains("NO_COVERAGE");
    }

    @Test
    void noMutantsAtLineIsNotFlagged() {
        List<BranchGateResult> results = new GateEngine().evaluate(List.of(DEF), List.of());
        BranchGateResult r = results.get(0);
        assertThat(r.mutantCount()).isZero();
        assertThat(r.flagged()).isFalse();
        assertThat(r.reason()).contains("no mutants generated");
    }

    @Test
    void aPrIsBlockedIfAnyOfItsBranchesIsFlagged() {
        BranchDef def2 = new BranchDef("pr0001", "pr0001-b1", "Foo", "com.example.Foo", 20, "b2", "WEAK");
        List<Mutation> mutations = List.of(
                new Mutation("com.example.Foo", 10, "KILLED", "MATH"),
                new Mutation("com.example.Foo", 20, "SURVIVED", "MATH"));
        GateEngine engine = new GateEngine();
        List<BranchGateResult> results = engine.evaluate(List.of(DEF, def2), mutations);
        assertThat(engine.isPrBlocked("pr0001", results)).isTrue();
        assertThat(engine.isPrBlocked("pr9999", results)).isFalse();
    }

    @Test
    void mutantsAtADifferentLineDoNotCountTowardThisBranch() {
        List<Mutation> mutations = List.of(new Mutation("com.example.Foo", 999, "SURVIVED", "MATH"));
        List<BranchGateResult> results = new GateEngine().evaluate(List.of(DEF), mutations);
        assertThat(results.get(0).mutantCount()).isZero();
        assertThat(results.get(0).flagged()).isFalse();
    }
}
