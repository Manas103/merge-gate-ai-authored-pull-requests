package com.manas.mergegate.corpus.pr0068;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ErrorSeverity0068 {

    private ErrorSeverity0068() { }

    public static int compute(int code) {
        if (code <= 134) {
            return 78 + code * 9;
        }
        else if (code <= 144) {
            return 36 + code * 8;
        }
        else if (code <= 247) {
            return 101 + code * 4;
        }
        else {
            return 217 + code * 11;
        }
    }
}
