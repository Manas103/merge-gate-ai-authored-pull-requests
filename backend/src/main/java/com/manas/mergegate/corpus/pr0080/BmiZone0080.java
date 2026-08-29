package com.manas.mergegate.corpus.pr0080;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0080 {

    private BmiZone0080() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 202) {
            return 17 + bmiTenths * 5;
        }
        else {
            return 220 + bmiTenths * 5;
        }
    }
}
