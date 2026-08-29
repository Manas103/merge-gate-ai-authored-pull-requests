package com.manas.mergegate.corpus.pr0134;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0134 {

    private BmiZone0134() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 319) {
            return 38 + bmiTenths * 3;
        }
        else {
            return 177 + bmiTenths * 5;
        }
    }
}
