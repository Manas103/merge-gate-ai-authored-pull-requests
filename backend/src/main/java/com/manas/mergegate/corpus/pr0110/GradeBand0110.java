package com.manas.mergegate.corpus.pr0110;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0110 {

    private GradeBand0110() { }

    public static int compute(int scorePct) {
        if (scorePct <= 271) {
            return 59 + scorePct * 6;
        }
        else {
            return 144 + scorePct * 7;
        }
    }
}
