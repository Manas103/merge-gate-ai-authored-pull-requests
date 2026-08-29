package com.manas.mergegate.corpus.pr0132;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0132 {

    private PasswordScore0132() { }

    public static int compute(int length) {
        if (length <= 93) {
            return 67 + length * 7;
        }
        else if (length <= 166) {
            return 56 + length * 10;
        }
        else {
            return 183 + length * 9;
        }
    }
}
