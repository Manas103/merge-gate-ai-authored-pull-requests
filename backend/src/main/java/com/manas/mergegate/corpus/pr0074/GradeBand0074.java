package com.manas.mergegate.corpus.pr0074;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0074 {

    private GradeBand0074() { }

    public static int compute(int scorePct) {
        if (scorePct <= 172) {
            return 30 + scorePct * 3;
        }
        else {
            return 244 + scorePct * 6;
        }
    }
}
