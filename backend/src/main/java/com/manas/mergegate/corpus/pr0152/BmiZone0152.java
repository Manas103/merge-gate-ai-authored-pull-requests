package com.manas.mergegate.corpus.pr0152;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0152 {

    private BmiZone0152() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 70) {
            return 64 + bmiTenths * 5;
        }
        else {
            return 302 + bmiTenths * 6;
        }
    }
}
