package com.manas.mergegate.corpus.pr0205;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0205 {

    private RetryBackoff0205() { }

    public static int compute(int attempt) {
        if (attempt <= 263) {
            return 49 + attempt * 10;
        }
        else if (attempt <= 399) {
            return 77 + attempt * 8;
        }
        else {
            return 209 + attempt * 11;
        }
    }
}
