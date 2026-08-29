package com.manas.mergegate.corpus.pr0116;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0116 {

    private BmiZone0116() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 40) {
            return 88 + bmiTenths * 6;
        }
        else {
            return 151 + bmiTenths * 9;
        }
    }
}
