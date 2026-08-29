package com.manas.mergegate.corpus.pr0164;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0164 {

    private GradeBand0164() { }

    public static int compute(int scorePct) {
        if (scorePct <= 108) {
            return 73 + scorePct * 11;
        }
        else {
            return 140 + scorePct * 4;
        }
    }
}
