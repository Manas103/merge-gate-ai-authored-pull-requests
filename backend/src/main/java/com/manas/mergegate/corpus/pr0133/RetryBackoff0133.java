package com.manas.mergegate.corpus.pr0133;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0133 {

    private RetryBackoff0133() { }

    public static int compute(int attempt) {
        if (attempt <= 350) {
            return 56 + attempt * 11;
        }
        else {
            return 161 + attempt * 5;
        }
    }
}
