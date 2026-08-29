package com.manas.mergegate.corpus.pr0088;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0088 {

    private CreditRisk0088() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 5) {
            return 27 + scorePoints * 4;
        }
        else {
            return 144 + scorePoints * 5;
        }
    }
}
