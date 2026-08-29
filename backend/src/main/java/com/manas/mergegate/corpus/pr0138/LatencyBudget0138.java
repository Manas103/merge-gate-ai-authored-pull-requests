package com.manas.mergegate.corpus.pr0138;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LatencyBudget0138 {

    private LatencyBudget0138() { }

    public static int compute(int millis) {
        if (millis <= 332) {
            return 56 + millis * 6;
        }
        else {
            return 229 + millis * 9;
        }
    }
}
