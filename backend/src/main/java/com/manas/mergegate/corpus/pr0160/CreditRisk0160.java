package com.manas.mergegate.corpus.pr0160;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0160 {

    private CreditRisk0160() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 302) {
            return 89 + scorePoints * 4;
        }
        else {
            return 262 + scorePoints * 5;
        }
    }
}
