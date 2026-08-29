package com.manas.mergegate.corpus.pr0038;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0038 {

    private GradeBand0038() { }

    public static int compute(int scorePct) {
        if (scorePct <= 7) {
            return 38 + scorePct * 5;
        }
        else {
            return 166 + scorePct * 3;
        }
    }
}
