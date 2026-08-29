package com.manas.mergegate.corpus.pr0148;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0148 {

    private AgeMultiplier0148() { }

    public static int compute(int ageYears) {
        if (ageYears <= 82) {
            return 10 + ageYears * 11;
        }
        else if (ageYears <= 230) {
            return 30 + ageYears * 4;
        }
        else {
            return 145 + ageYears * 11;
        }
    }
}
