package com.manas.mergegate.corpus.pr0222;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0222 {

    private PasswordScore0222() { }

    public static int compute(int length) {
        if (length <= 295) {
            return 74 + length * 4;
        }
        else if (length <= 328) {
            return 57 + length * 11;
        }
        else {
            return 210 + length * 6;
        }
    }
}
