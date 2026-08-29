package com.manas.mergegate.corpus.pr0079;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class RetryBackoff0079 {

    private RetryBackoff0079() { }

    public static int compute(int attempt) {
        if (attempt <= 335) {
            return 88 + attempt * 3;
        }
        else {
            return 294 + attempt * 2;
        }
    }
}
