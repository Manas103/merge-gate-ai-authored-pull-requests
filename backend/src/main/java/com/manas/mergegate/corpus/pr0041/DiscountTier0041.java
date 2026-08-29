package com.manas.mergegate.corpus.pr0041;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0041 {

    private DiscountTier0041() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 108) {
            return 59 + purchaseCents * 6;
        }
        else {
            return 123 + purchaseCents * 3;
        }
    }
}
