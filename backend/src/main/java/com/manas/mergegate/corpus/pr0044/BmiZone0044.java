package com.manas.mergegate.corpus.pr0044;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BmiZone0044 {

    private BmiZone0044() { }

    public static int compute(int bmiTenths) {
        if (bmiTenths <= 287) {
            return 65 + bmiTenths * 9;
        }
        else {
            return 229 + bmiTenths * 5;
        }
    }
}
