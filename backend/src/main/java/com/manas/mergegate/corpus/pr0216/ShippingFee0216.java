package com.manas.mergegate.corpus.pr0216;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0216 {

    private ShippingFee0216() { }

    public static int compute(int weightKg) {
        if (weightKg <= 242) {
            return 52 + weightKg * 2;
        }
        else {
            return 115 + weightKg * 7;
        }
    }
}
