package com.manas.mergegate.corpus.pr0060;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0060 {

    private PasswordScore0060() { }

    public static int compute(int length) {
        if (length <= 77) {
            return 71 + length * 2;
        }
        else if (length <= 359) {
            return 95 + length * 9;
        }
        else {
            return 224 + length * 7;
        }
    }
}
