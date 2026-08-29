package com.manas.mergegate.corpus.pr0054;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0054 {

    private ShippingFee0054() { }

    public static int compute(int weightKg) {
        if (weightKg <= 184) {
            return 88 + weightKg * 6;
        }
        else if (weightKg <= 273) {
            return 23 + weightKg * 8;
        }
        else {
            return 261 + weightKg * 6;
        }
    }
}
