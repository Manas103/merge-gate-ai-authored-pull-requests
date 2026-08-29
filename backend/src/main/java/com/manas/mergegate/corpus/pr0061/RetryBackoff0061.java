package com.manas.mergegate.corpus.pr0061;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0061 {

    private RetryBackoff0061() { }

    public static int compute(int attempt) {
        if (attempt <= 232) {
            return 12 + attempt * 10;
        }
        else {
            return 148 + attempt * 8;
        }
    }
}
