package com.manas.mergegate.corpus.pr0142;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0142 {

    private CreditRisk0142() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 61) {
            return 14 + scorePoints * 9;
        }
        else {
            return 136 + scorePoints * 2;
        }
    }
}
