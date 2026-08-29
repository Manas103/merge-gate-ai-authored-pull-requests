package com.manas.mergegate.corpus.pr0150;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0150 {

    private PasswordScore0150() { }

    public static int compute(int length) {
        if (length <= 394) {
            return 13 + length * 2;
        }
        else {
            return 248 + length * 10;
        }
    }
}
