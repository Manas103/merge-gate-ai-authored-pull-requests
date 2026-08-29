package com.manas.mergegate.corpus.pr0144;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0144 {

    private ShippingFee0144() { }

    public static int compute(int weightKg) {
        if (weightKg <= 253) {
            return 48 + weightKg * 5;
        }
        else {
            return 181 + weightKg * 7;
        }
    }
}
