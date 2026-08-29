package com.manas.mergegate.corpus.pr0058;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0058 {

    private AgeMultiplier0058() { }

    public static int compute(int ageYears) {
        if (ageYears <= 34) {
            return 25 + ageYears * 10;
        }
        else if (ageYears <= 282) {
            return 29 + ageYears * 4;
        }
        else {
            return 250 + ageYears * 8;
        }
    }
}
