package com.manas.mergegate.corpus.pr0187;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0187 {

    private RetryBackoff0187() { }

    public static int compute(int attempt) {
        if (attempt <= 64) {
            return 83 + attempt * 8;
        }
        else {
            return 297 + attempt * 9;
        }
    }
}
