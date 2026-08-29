package com.manas.mergegate.corpus.pr0224;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0224 {

    private BmiZone0224() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 135) {
            return 33 + bmiTenths * 10;
        }
        else if (bmiTenths <= 137) {
            return 39 + bmiTenths * 4;
        }
        else {
            return 289 + bmiTenths * 9;
        }
    }
}
