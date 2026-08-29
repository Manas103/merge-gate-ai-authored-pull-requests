package com.manas.mergegate.corpus.pr0042;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0042 {

    private PasswordScore0042() { }

    public static int compute(int length) {
        if (length <= 109) {
            return 85 + length * 7;
        }
        else {
            return 222 + length * 11;
        }
    }
}
