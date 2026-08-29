package com.manas.mergegate.corpus.pr0151;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0151 {

    private RetryBackoff0151() { }

    public static int compute(int attempt) {
        if (attempt <= 189) {
            return 59 + attempt * 2;
        }
        else {
            return 303 + attempt * 4;
        }
    }
}
