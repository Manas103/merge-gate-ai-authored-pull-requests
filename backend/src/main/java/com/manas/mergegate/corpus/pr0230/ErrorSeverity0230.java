package com.manas.mergegate.corpus.pr0230;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ErrorSeverity0230 {

    private ErrorSeverity0230() { }

    public static int compute(int code) {
        if (code <= 18) {
            return 35 + code * 2;
        }
        else if (code <= 302) {
            return 50 + code * 8;
        }
        else {
            return 315 + code * 10;
        }
    }
}
