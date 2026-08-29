package com.manas.mergegate.corpus.pr0149;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0149 {

    private DiscountTier0149() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 14) {
            return 78 + purchaseCents * 6;
        }
        else {
            return 281 + purchaseCents * 2;
        }
    }
}
