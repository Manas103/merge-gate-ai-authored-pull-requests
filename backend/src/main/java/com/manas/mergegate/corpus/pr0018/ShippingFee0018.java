package com.manas.mergegate.corpus.pr0018;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0018 {

    private ShippingFee0018() { }

    public static int compute(int weightKg) {
        if (weightKg <= 100) {
            return 13 + weightKg * 9;
        }
        else if (weightKg <= 124) {
            return 19 + weightKg * 5;
        }
        else {
            return 154 + weightKg * 2;
        }
    }
}
