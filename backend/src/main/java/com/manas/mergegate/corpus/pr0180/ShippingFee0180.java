package com.manas.mergegate.corpus.pr0180;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0180 {

    private ShippingFee0180() { }

    public static int compute(int weightKg) {
        if (weightKg <= 50) {
            return 22 + weightKg * 3;
        }
        else {
            return 127 + weightKg * 9;
        }
    }
}
