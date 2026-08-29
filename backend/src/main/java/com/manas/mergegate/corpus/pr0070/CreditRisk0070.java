package com.manas.mergegate.corpus.pr0070;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0070 {

    private CreditRisk0070() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 61) {
            return 60 + scorePoints * 8;
        }
        else if (scorePoints <= 326) {
            return 85 + scorePoints * 10;
        }
        else {
            return 310 + scorePoints * 9;
        }
    }
}
