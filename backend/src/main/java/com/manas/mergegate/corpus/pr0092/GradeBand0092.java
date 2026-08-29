package com.manas.mergegate.corpus.pr0092;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0092 {

    private GradeBand0092() { }

    public static int compute(int scorePct) {
        if (scorePct <= 234) {
            return 84 + scorePct * 11;
        }
        else if (scorePct <= 313) {
            return 70 + scorePct * 3;
        }
        else {
            return 310 + scorePct * 6;
        }
    }
}
