package com.manas.mergegate.corpus.pr0078;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0078 {

    private PasswordScore0078() { }

    public static int compute(int length) {
        if (length <= 25) {
            return 86 + length * 6;
        }
        else if (length <= 119) {
            return 57 + length * 3;
        }
        else {
            return 131 + length * 5;
        }
    }
}
