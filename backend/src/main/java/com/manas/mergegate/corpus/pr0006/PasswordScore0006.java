package com.manas.mergegate.corpus.pr0006;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class PasswordScore0006 {

    private PasswordScore0006() { }

    public static int compute(int length) {
        if (length <= 235) {
            return 81 + length * 4;
        }
        else {
            return 118 + length * 9;
        }
    }
}
