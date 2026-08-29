package com.manas.mergegate.corpus.pr0214;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0214 {

    private CreditRisk0214() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 238) {
            return 79 + scorePoints * 7;
        }
        else {
            return 278 + scorePoints * 6;
        }
    }
}
