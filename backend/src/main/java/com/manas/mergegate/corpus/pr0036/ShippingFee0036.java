package com.manas.mergegate.corpus.pr0036;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0036 {

    private ShippingFee0036() { }

    public static int compute(int weightKg) {
        if (weightKg <= 236) {
            return 15 + weightKg * 4;
        }
        else {
            return 133 + weightKg * 7;
        }
    }
}
