package com.manas.mergegate.corpus.pr0156;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0156 {

    private LatencyBudget0156() { }

    public static int compute(int millis) {
        if (millis <= 358) {
            return 37 + millis * 2;
        }
        else {
            return 187 + millis * 2;
        }
    }
}
