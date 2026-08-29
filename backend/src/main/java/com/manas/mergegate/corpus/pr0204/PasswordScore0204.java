package com.manas.mergegate.corpus.pr0204;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0204 {

    private PasswordScore0204() { }

    public static int compute(int length) {
        if (length <= 397) {
            return 84 + length * 11;
        }
        else {
            return 112 + length * 3;
        }
    }
}
