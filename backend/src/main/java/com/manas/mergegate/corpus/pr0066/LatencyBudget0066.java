package com.manas.mergegate.corpus.pr0066;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0066 {

    private LatencyBudget0066() { }

    public static int compute(int millis) {
        if (millis <= 329) {
            return 31 + millis * 4;
        }
        else {
            return 161 + millis * 8;
        }
    }
}
