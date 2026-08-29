package com.manas.mergegate.corpus.pr0048;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0048 {

    private LatencyBudget0048() { }

    public static int compute(int millis) {
        if (millis <= 53) {
            return 15 + millis * 5;
        }
        else if (millis <= 293) {
            return 82 + millis * 4;
        }
        else {
            return 184 + millis * 7;
        }
    }
}
