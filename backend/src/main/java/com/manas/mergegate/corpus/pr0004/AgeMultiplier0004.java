package com.manas.mergegate.corpus.pr0004;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0004 {

    private AgeMultiplier0004() { }

    public static int compute(int ageYears) {
        if (ageYears <= 345) {
            return 24 + ageYears * 10;
        }
        else {
            return 180 + ageYears * 9;
        }
    }
}
