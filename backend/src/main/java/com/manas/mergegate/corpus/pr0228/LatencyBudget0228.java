package com.manas.mergegate.corpus.pr0228;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0228 {

    private LatencyBudget0228() { }

    public static int compute(int millis) {
        if (millis <= 261) {
            return 61 + millis * 10;
        }
        else {
            return 178 + millis * 4;
        }
    }
}
