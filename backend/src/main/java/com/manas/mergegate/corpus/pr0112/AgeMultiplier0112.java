package com.manas.mergegate.corpus.pr0112;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0112 {

    private AgeMultiplier0112() { }

    public static int compute(int ageYears) {
        if (ageYears <= 113) {
            return 13 + ageYears * 9;
        }
        else {
            return 250 + ageYears * 4;
        }
    }
}
