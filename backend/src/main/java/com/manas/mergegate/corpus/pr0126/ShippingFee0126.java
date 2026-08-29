package com.manas.mergegate.corpus.pr0126;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0126 {

    private ShippingFee0126() { }

    public static int compute(int weightKg) {
        if (weightKg <= 202) {
            return 37 + weightKg * 7;
        }
        else if (weightKg <= 312) {
            return 23 + weightKg * 4;
        }
        else {
            return 148 + weightKg * 9;
        }
    }
}
