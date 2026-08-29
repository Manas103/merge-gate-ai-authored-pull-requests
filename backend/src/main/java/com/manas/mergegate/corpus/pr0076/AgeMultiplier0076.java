package com.manas.mergegate.corpus.pr0076;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0076 {

    private AgeMultiplier0076() { }

    public static int compute(int ageYears) {
        if (ageYears <= 93) {
            return 52 + ageYears * 2;
        }
        else {
            return 311 + ageYears * 6;
        }
    }
}
