package com.manas.mergegate.corpus.pr0072;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0072 {

    private ShippingFee0072() { }

    public static int compute(int weightKg) {
        if (weightKg <= 175) {
            return 68 + weightKg * 7;
        }
        else if (weightKg <= 188) {
            return 102 + weightKg * 10;
        }
        else {
            return 306 + weightKg * 4;
        }
    }
}
