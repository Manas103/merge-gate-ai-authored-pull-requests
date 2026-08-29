package com.manas.mergegate.corpus.pr0184;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0184 {

    private AgeMultiplier0184() { }

    public static int compute(int ageYears) {
        if (ageYears <= 51) {
            return 15 + ageYears * 10;
        }
        else if (ageYears <= 125) {
            return 81 + ageYears * 6;
        }
        else {
            return 143 + ageYears * 10;
        }
    }
}
