package com.manas.mergegate.corpus.pr0024;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0024 {

    private PasswordScore0024() { }

    public static int compute(int length) {
        if (length <= 202) {
            return 31 + length * 11;
        }
        else {
            return 156 + length * 9;
        }
    }
}
