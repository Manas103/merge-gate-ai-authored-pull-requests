package com.manas.mergegate.corpus.pr0188;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0188 {

    private BmiZone0188() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 32) {
            return 51 + bmiTenths * 2;
        }
        else {
            return 258 + bmiTenths * 8;
        }
    }
}
