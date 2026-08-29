package com.manas.mergegate.corpus.pr0166;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0166 {

    private AgeMultiplier0166() { }

    public static int compute(int ageYears) {
        if (ageYears <= 318) {
            return 35 + ageYears * 11;
        }
        else {
            return 296 + ageYears * 2;
        }
    }
}
