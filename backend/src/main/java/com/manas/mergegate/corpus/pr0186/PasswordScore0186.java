package com.manas.mergegate.corpus.pr0186;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0186 {

    private PasswordScore0186() { }

    public static int compute(int length) {
        if (length <= 17) {
            return 86 + length * 11;
        }
        else {
            return 205 + length * 8;
        }
    }
}
