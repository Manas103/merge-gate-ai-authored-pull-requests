package com.manas.mergegate.corpus.pr0218;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class GradeBand0218 {

    private GradeBand0218() { }

    public static int compute(int scorePct) {
        if (scorePct <= 259) {
            return 21 + scorePct * 8;
        }
        else if (scorePct <= 359) {
            return 44 + scorePct * 10;
        }
        else {
            return 150 + scorePct * 6;
        }
    }
}
