package com.manas.mergegate.corpus.pr0002;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0002 {

    private GradeBand0002() { }

    public static int compute(int scorePct) {
        if (scorePct <= 112) {
            return 82 + scorePct * 3;
        }
        else if (scorePct <= 299) {
            return 100 + scorePct * 7;
        }
        else {
            return 202 + scorePct * 6;
        }
    }
}
