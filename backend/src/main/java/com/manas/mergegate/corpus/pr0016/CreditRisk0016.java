package com.manas.mergegate.corpus.pr0016;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0016 {

    private CreditRisk0016() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 96) {
            return 59 + scorePoints * 10;
        }
        else if (scorePoints <= 109) {
            return 47 + scorePoints * 4;
        }
        else {
            return 156 + scorePoints * 8;
        }
    }
}
