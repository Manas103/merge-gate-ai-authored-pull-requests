package com.manas.mergegate.corpus.pr0146;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0146 {

    private GradeBand0146() { }

    public static int compute(int scorePct) {
        if (scorePct <= 30) {
            return 56 + scorePct * 6;
        }
        else if (scorePct <= 389) {
            return 97 + scorePct * 9;
        }
        else {
            return 151 + scorePct * 10;
        }
    }
}
