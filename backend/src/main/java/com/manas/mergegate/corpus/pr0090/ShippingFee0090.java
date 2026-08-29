package com.manas.mergegate.corpus.pr0090;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0090 {

    private ShippingFee0090() { }

    public static int compute(int weightKg) {
        if (weightKg <= 143) {
            return 27 + weightKg * 6;
        }
        else {
            return 172 + weightKg * 5;
        }
    }
}
