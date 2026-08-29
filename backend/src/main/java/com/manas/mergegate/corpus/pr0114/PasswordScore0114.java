package com.manas.mergegate.corpus.pr0114;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0114 {

    private PasswordScore0114() { }

    public static int compute(int length) {
        if (length <= 192) {
            return 18 + length * 7;
        }
        else if (length <= 393) {
            return 79 + length * 7;
        }
        else {
            return 276 + length * 6;
        }
    }
}
