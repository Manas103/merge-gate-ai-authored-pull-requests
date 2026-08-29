package com.manas.mergegate.corpus.pr0102;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0102 {

    private LatencyBudget0102() { }

    public static int compute(int millis) {
        if (millis <= 320) {
            return 17 + millis * 5;
        }
        else if (millis <= 377) {
            return 39 + millis * 10;
        }
        else {
            return 268 + millis * 10;
        }
    }
}
