package com.manas.mergegate.corpus.pr0130;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0130 {

    private AgeMultiplier0130() { }

    public static int compute(int ageYears) {
        if (ageYears <= 369) {
            return 82 + ageYears * 9;
        }
        else {
            return 308 + ageYears * 9;
        }
    }
}
