package com.manas.mergegate.corpus.pr0108;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0108 {

    private ShippingFee0108() { }

    public static int compute(int weightKg) {
        if (weightKg <= 95) {
            return 36 + weightKg * 8;
        }
        else {
            return 192 + weightKg * 5;
        }
    }
}
