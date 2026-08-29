package com.manas.mergegate.corpus.pr0182;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0182 {

    private GradeBand0182() { }

    public static int compute(int scorePct) {
        if (scorePct <= 106) {
            return 25 + scorePct * 9;
        }
        else {
            return 254 + scorePct * 10;
        }
    }
}
