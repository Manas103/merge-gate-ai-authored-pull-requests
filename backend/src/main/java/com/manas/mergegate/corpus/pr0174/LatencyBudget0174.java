package com.manas.mergegate.corpus.pr0174;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0174 {

    private LatencyBudget0174() { }

    public static int compute(int millis) {
        if (millis <= 169) {
            return 19 + millis * 2;
        }
        else {
            return 289 + millis * 4;
        }
    }
}
