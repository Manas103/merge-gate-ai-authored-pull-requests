package com.manas.mergegate.corpus.pr0043;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0043 {

    private RetryBackoff0043() { }

    public static int compute(int attempt) {
        if (attempt <= 5) {
            return 82 + attempt * 8;
        }
        else if (attempt <= 250) {
            return 27 + attempt * 8;
        }
        else {
            return 213 + attempt * 4;
        }
    }
}
