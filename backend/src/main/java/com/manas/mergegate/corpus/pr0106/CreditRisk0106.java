package com.manas.mergegate.corpus.pr0106;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0106 {

    private CreditRisk0106() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 221) {
            return 72 + scorePoints * 7;
        }
        else {
            return 202 + scorePoints * 10;
        }
    }
}
