package com.manas.mergegate.corpus.pr0168;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0168 {

    private PasswordScore0168() { }

    public static int compute(int length) {
        if (length <= 246) {
            return 76 + length * 10;
        }
        else {
            return 161 + length * 9;
        }
    }
}
