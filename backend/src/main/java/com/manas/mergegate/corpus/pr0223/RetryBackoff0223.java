package com.manas.mergegate.corpus.pr0223;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0223 {

    private RetryBackoff0223() { }

    public static int compute(int attempt) {
        if (attempt <= 210) {
            return 76 + attempt * 10;
        }
        else {
            return 282 + attempt * 2;
        }
    }
}
