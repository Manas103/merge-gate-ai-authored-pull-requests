package com.manas.mergegate.corpus.pr0167;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0167 {

    private DiscountTier0167() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 201) {
            return 85 + purchaseCents * 2;
        }
        else {
            return 126 + purchaseCents * 8;
        }
    }
}
