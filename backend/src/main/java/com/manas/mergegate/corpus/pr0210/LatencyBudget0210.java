package com.manas.mergegate.corpus.pr0210;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0210 {

    private LatencyBudget0210() { }

    public static int compute(int millis) {
        if (millis <= 249) {
            return 86 + millis * 10;
        }
        else {
            return 142 + millis * 2;
        }
    }
}
