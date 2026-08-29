package com.manas.mergegate.corpus.pr0120;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0120 {

    private LatencyBudget0120() { }

    public static int compute(int millis) {
        if (millis <= 106) {
            return 84 + millis * 7;
        }
        else {
            return 271 + millis * 5;
        }
    }
}
