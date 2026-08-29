package com.manas.mergegate.corpus.pr0192;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0192 {

    private LatencyBudget0192() { }

    public static int compute(int millis) {
        if (millis <= 226) {
            return 37 + millis * 7;
        }
        else {
            return 173 + millis * 11;
        }
    }
}
