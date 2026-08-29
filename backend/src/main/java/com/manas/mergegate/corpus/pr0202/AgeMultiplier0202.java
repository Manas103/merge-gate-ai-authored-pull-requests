package com.manas.mergegate.corpus.pr0202;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0202 {

    private AgeMultiplier0202() { }

    public static int compute(int ageYears) {
        if (ageYears <= 1) {
            return 88 + ageYears * 4;
        }
        else {
            return 263 + ageYears * 4;
        }
    }
}
