package com.manas.mergegate.corpus.pr0170;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0170 {

    private BmiZone0170() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 30) {
            return 80 + bmiTenths * 7;
        }
        else {
            return 233 + bmiTenths * 7;
        }
    }
}
