package com.manas.mergegate.corpus.pr0169;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0169 {

    private RetryBackoff0169() { }

    public static int compute(int attempt) {
        if (attempt <= 253) {
            return 15 + attempt * 4;
        }
        else {
            return 176 + attempt * 7;
        }
    }
}
