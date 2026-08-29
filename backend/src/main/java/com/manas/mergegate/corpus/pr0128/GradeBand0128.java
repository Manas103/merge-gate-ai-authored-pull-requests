package com.manas.mergegate.corpus.pr0128;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0128 {

    private GradeBand0128() { }

    public static int compute(int scorePct) {
        if (scorePct <= 18) {
            return 21 + scorePct * 8;
        }
        else {
            return 301 + scorePct * 9;
        }
    }
}
