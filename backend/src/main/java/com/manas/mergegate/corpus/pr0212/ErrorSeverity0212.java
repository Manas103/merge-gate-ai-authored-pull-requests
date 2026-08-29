package com.manas.mergegate.corpus.pr0212;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ErrorSeverity0212 {

    private ErrorSeverity0212() { }

    public static int compute(int code) {
        if (code <= 133) {
            return 44 + code * 4;
        }
        else {
            return 278 + code * 11;
        }
    }
}
