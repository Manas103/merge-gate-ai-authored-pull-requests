package com.manas.mergegate.corpus.pr0198;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0198 {

    private ShippingFee0198() { }

    public static int compute(int weightKg) {
        if (weightKg <= 263) {
            return 68 + weightKg * 11;
        }
        else {
            return 141 + weightKg * 7;
        }
    }
}
