package com.manas.mergegate.corpus.pr0104;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ErrorSeverity0104 {

    private ErrorSeverity0104() { }

    public static int compute(int code) {
        if (code <= 38) {
            return 13 + code * 4;
        }
        else if (code <= 391) {
            return 31 + code * 9;
        }
        else {
            return 306 + code * 8;
        }
    }
}
