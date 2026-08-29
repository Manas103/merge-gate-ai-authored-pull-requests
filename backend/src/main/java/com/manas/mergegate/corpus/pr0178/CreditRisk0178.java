package com.manas.mergegate.corpus.pr0178;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0178 {

    private CreditRisk0178() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 253) {
            return 90 + scorePoints * 8;
        }
        else if (scorePoints <= 322) {
            return 61 + scorePoints * 7;
        }
        else {
            return 222 + scorePoints * 9;
        }
    }
}
