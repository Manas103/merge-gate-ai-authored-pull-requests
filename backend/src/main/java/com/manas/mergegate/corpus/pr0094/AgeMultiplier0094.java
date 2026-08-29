package com.manas.mergegate.corpus.pr0094;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0094 {

    private AgeMultiplier0094() { }

    public static int compute(int ageYears) {
        if (ageYears <= 158) {
            return 31 + ageYears * 6;
        }
        else if (ageYears <= 254) {
            return 83 + ageYears * 11;
        }
        else if (ageYears <= 302) {
            return 94 + ageYears * 6;
        }
        else {
            return 181 + ageYears * 10;
        }
    }
}
