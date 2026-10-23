package com.manas.mergegate.ordering;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class OrderingOracleReportTest {

    @Test
    void referenceLogPassesBothTheExistingTestAndTheOracle() {
        assertTrue(PatchCorpus.existingUnitTestPasses(new ReferenceAppendLog()));
        OrderingDurabilityOracle.Verdict v = OrderingDurabilityOracle.check(ReferenceAppendLog::new, 1, 500, 8, 50);
        assertTrue(v.passed(), v.firstFailure());
    }

    @Test
    void everyBugKindInTheCorpusIsActuallyWrong() {
        for (PatchCorpus.Patch patch : PatchCorpus.all()) {
            if (patch.bugKind().equals("none")) continue;
            Supplier<AppendLog> factory = patch.factory();
            boolean existingPassed = PatchCorpus.existingUnitTestPasses(factory.get());
            OrderingDurabilityOracle.Verdict verdict = OrderingDurabilityOracle.check(factory, 42, 500, 8, 50);
            assertFalse(existingPassed && verdict.passed(),
                    patch.name() + " is supposed to be buggy, but neither check caught it");
        }
    }

    @Test
    void contrastPatchesAreCaughtByTheExistingTestAlready() {
        for (PatchCorpus.Patch patch : PatchCorpus.all()) {
            if (patch.bugKind().equals("immediate-offset-bug") || patch.bugKind().equals("immediate-storage-bug")) {
                assertFalse(PatchCorpus.existingUnitTestPasses(patch.factory().get()),
                        patch.name() + " was meant to be an immediate, existing-test-catches-it contrast case");
            }
        }
    }

    @Test
    void reportCountsAreInternallyConsistent() {
        List<OrderingOracleReport.Row> rows = OrderingOracleReport.run(42);
        long both = OrderingOracleReport.countPassedExistingButCaughtByOracle(rows);
        assertTrue(both > 0 && both <= rows.size());
        long correctPatchesWronglyFlagged = rows.stream()
                .filter(r -> r.bugKind().equals("none") && r.oracleCaughtIt())
                .count();
        assertEquals(0, correctPatchesWronglyFlagged, "the oracle must not flag a correct implementation");
    }
}
