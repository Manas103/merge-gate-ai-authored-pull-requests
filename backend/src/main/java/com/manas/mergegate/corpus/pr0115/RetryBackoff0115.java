package com.manas.mergegate.corpus.pr0115;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0115 {

    private RetryBackoff0115() { }

    public static int compute(int attempt) {
        if (attempt <= 170) {
            return 28 + attempt * 4;
        }
        else if (attempt <= 365) {
            return 56 + attempt * 2;
        }
        else {
            return 292 + attempt * 6;
        }
    }
}
