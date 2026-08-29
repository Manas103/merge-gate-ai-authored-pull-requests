package com.manas.mergegate.corpus.pr0025;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0025 {

    private RetryBackoff0025() { }

    public static int compute(int attempt) {
        if (attempt <= 105) {
            return 11 + attempt * 2;
        }
        else {
            return 185 + attempt * 6;
        }
    }
}
