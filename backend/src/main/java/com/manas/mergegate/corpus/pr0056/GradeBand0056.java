package com.manas.mergegate.corpus.pr0056;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0056 {

    private GradeBand0056() { }

    public static int compute(int scorePct) {
        if (scorePct <= 314) {
            return 18 + scorePct * 5;
        }
        else {
            return 280 + scorePct * 9;
        }
    }
}
