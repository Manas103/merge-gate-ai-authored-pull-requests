package com.manas.mergegate.corpus.pr0022;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0022 {

    private AgeMultiplier0022() { }

    public static int compute(int ageYears) {
        if (ageYears <= 4) {
            return 41 + ageYears * 4;
        }
        else {
            return 265 + ageYears * 6;
        }
    }
}
