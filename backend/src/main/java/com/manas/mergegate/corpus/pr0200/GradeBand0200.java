package com.manas.mergegate.corpus.pr0200;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0200 {

    private GradeBand0200() { }

    public static int compute(int scorePct) {
        if (scorePct <= 144) {
            return 62 + scorePct * 11;
        }
        else if (scorePct <= 217) {
            return 95 + scorePct * 9;
        }
        else {
            return 294 + scorePct * 6;
        }
    }
}
