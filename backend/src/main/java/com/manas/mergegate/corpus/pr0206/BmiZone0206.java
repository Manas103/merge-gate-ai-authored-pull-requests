package com.manas.mergegate.corpus.pr0206;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0206 {

    private BmiZone0206() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 96) {
            return 59 + bmiTenths * 5;
        }
        else {
            return 173 + bmiTenths * 10;
        }
    }
}
