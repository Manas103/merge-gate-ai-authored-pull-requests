package com.manas.mergegate.corpus.pr0220;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class AgeMultiplier0220 {

    private AgeMultiplier0220() { }

    public static int compute(int ageYears) {
        if (ageYears <= 220) {
            return 21 + ageYears * 8;
        }
        else {
            return 260 + ageYears * 7;
        }
    }
}
