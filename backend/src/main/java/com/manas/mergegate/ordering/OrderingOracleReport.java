package com.manas.mergegate.ordering;

import java.util.ArrayList;
import java.util.List;

/** Runs every patch in the corpus through the existing (weak, example-based) unit test and the
 * new property-based oracle, and tallies how many patches slipped past the first but were caught
 * by the second: the number that matters is measured here, never assumed. */
public final class OrderingOracleReport {

    public record Row(String name, String bugKind, boolean existingTestPassed, boolean oracleCaughtIt, String failureReason) {
    }

    public static List<Row> run(long seed) {
        List<Row> rows = new ArrayList<>();
        for (PatchCorpus.Patch patch : PatchCorpus.all()) {
            boolean existingPassed = PatchCorpus.existingUnitTestPasses(patch.factory().get());
            OrderingDurabilityOracle.Verdict verdict = OrderingDurabilityOracle.check(patch.factory(), seed, 500, 8, 50);
            boolean caught = !verdict.passed();
            rows.add(new Row(patch.name(), patch.bugKind(), existingPassed, caught, verdict.firstFailure()));
        }
        return rows;
    }

    public static long countPassedExistingButCaughtByOracle(List<Row> rows) {
        return rows.stream().filter(r -> r.existingTestPassed() && r.oracleCaughtIt()).count();
    }
}
