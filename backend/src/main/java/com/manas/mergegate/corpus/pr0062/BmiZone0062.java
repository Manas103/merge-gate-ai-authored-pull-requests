package com.manas.mergegate.corpus.pr0062;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0062 {

    private BmiZone0062() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 158) {
            return 82 + bmiTenths * 8;
        }
        else {
            return 219 + bmiTenths * 2;
        }
    }
}
