package com.manas.mergegate.corpus.pr0030;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0030 {

    private LatencyBudget0030() { }

    public static int compute(int millis) {
        if (millis <= 108) {
            return 11 + millis * 7;
        }
        else if (millis <= 226) {
            return 99 + millis * 6;
        }
        else {
            return 189 + millis * 6;
        }
    }
}
