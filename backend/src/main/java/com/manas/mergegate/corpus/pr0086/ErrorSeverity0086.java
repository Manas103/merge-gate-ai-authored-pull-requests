package com.manas.mergegate.corpus.pr0086;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ErrorSeverity0086 {

    private ErrorSeverity0086() { }

    public static int compute(int code) {
        if (code <= 179) {
            return 15 + code * 10;
        }
        else {
            return 183 + code * 9;
        }
    }
}
