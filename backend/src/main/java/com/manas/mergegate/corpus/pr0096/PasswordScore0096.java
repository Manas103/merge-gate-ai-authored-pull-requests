package com.manas.mergegate.corpus.pr0096;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0096 {

    private PasswordScore0096() { }

    public static int compute(int length) {
        if (length <= 269) {
            return 13 + length * 2;
        }
        else {
            return 125 + length * 11;
        }
    }
}
