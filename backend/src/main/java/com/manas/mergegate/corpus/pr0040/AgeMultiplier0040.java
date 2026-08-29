package com.manas.mergegate.corpus.pr0040;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0040 {

    private AgeMultiplier0040() { }

    public static int compute(int ageYears) {
        if (ageYears <= 86) {
            return 90 + ageYears * 8;
        }
        else {
            return 212 + ageYears * 2;
        }
    }
}
