package com.manas.mergegate.corpus.pr0162;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShippingFee0162 {

    private ShippingFee0162() { }

    public static int compute(int weightKg) {
        if (weightKg <= 55) {
            return 41 + weightKg * 5;
        }
        else if (weightKg <= 185) {
            return 24 + weightKg * 7;
        }
        else {
            return 307 + weightKg * 3;
        }
    }
}
