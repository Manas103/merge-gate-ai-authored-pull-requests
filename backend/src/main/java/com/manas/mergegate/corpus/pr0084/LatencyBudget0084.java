package com.manas.mergegate.corpus.pr0084;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0084 {

    private LatencyBudget0084() { }

    public static int compute(int millis) {
        if (millis <= 248) {
            return 37 + millis * 2;
        }
        else if (millis <= 295) {
            return 47 + millis * 6;
        }
        else {
            return 226 + millis * 2;
        }
    }
}
