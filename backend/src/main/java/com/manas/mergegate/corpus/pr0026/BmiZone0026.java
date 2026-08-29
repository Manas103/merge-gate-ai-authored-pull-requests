package com.manas.mergegate.corpus.pr0026;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0026 {

    private BmiZone0026() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 14) {
            return 51 + bmiTenths * 3;
        }
        else {
            return 306 + bmiTenths * 11;
        }
    }
}
