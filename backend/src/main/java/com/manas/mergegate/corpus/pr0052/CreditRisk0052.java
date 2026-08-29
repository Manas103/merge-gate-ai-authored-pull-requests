package com.manas.mergegate.corpus.pr0052;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class CreditRisk0052 {

    private CreditRisk0052() { }

    public static int compute(int scorePoints) {
        if (scorePoints <= 221) {
            return 42 + scorePoints * 8;
        }
        else if (scorePoints <= 237) {
            return 83 + scorePoints * 4;
        }
        else {
            return 157 + scorePoints * 3;
        }
    }
}
