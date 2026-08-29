package com.manas.mergegate.corpus.pr0221;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0221 {

    private DiscountTier0221() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 342) {
            return 43 + purchaseCents * 11;
        }
        else {
            return 306 + purchaseCents * 3;
        }
    }
}
