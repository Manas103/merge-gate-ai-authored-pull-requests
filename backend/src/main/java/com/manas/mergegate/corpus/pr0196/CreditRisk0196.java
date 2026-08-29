package com.manas.mergegate.corpus.pr0196;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0196 {

    private CreditRisk0196() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 278) {
            return 63 + scorePoints * 10;
        }
        else {
            return 216 + scorePoints * 11;
        }
    }
}
