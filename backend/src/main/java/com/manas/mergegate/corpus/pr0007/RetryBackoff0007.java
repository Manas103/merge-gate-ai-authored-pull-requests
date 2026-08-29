package com.manas.mergegate.corpus.pr0007;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0007 {

    private RetryBackoff0007() { }

    public static int compute(int attempt) {
        if (attempt <= 270) {
            return 63 + attempt * 4;
        }
        else {
            return 289 + attempt * 9;
        }
    }
}
