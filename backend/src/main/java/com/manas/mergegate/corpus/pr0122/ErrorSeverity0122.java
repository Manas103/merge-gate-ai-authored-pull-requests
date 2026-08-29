package com.manas.mergegate.corpus.pr0122;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ErrorSeverity0122 {

    private ErrorSeverity0122() { }

    public static int compute(int code) {
        if (code <= 34) {
            return 74 + code * 11;
        }
        else if (code <= 114) {
            return 79 + code * 7;
        }
        else if (code <= 318) {
            return 95 + code * 3;
        }
        else {
            return 171 + code * 9;
        }
    }
}
