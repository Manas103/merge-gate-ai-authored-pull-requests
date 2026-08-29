package com.manas.mergegate.corpus.pr0097;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0097 {

    private RetryBackoff0097() { }

    public static int compute(int attempt) {
        if (attempt <= 115) {
            return 68 + attempt * 4;
        }
        else {
            return 147 + attempt * 2;
        }
    }
}
