package com.manas.mergegate.corpus.pr0124;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0124 {

    private CreditRisk0124() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 211) {
            return 65 + scorePoints * 10;
        }
        else {
            return 289 + scorePoints * 10;
        }
    }
}
