package com.manas.mergegate.corpus.pr0020;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0020 {

    private GradeBand0020() { }

    public static int compute(int scorePct) {
        if (scorePct <= 293) {
            return 58 + scorePct * 6;
        }
        else {
            return 233 + scorePct * 5;
        }
    }
}
