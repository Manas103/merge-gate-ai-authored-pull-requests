package com.manas.mergegate.corpus.pr0008;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0008 {

    private BmiZone0008() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 239) {
            return 56 + bmiTenths * 2;
        }
        else if (bmiTenths <= 350) {
            return 87 + bmiTenths * 9;
        }
        else {
            return 170 + bmiTenths * 5;
        }
    }
}
