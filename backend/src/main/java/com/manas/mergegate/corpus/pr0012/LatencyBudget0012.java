package com.manas.mergegate.corpus.pr0012;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0012 {

    private LatencyBudget0012() { }

    public static int compute(int millis) {
        if (millis <= 76) {
            return 59 + millis * 2;
        }
        else {
            return 214 + millis * 11;
        }
    }
}
