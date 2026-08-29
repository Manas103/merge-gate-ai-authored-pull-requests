package com.manas.mergegate.corpus.pr0098;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0098 {

    private BmiZone0098() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 48) {
            return 42 + bmiTenths * 8;
        }
        else if (bmiTenths <= 270) {
            return 76 + bmiTenths * 3;
        }
        else {
            return 161 + bmiTenths * 8;
        }
    }
}
