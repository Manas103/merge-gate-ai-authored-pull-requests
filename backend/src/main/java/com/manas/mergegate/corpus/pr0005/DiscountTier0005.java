package com.manas.mergegate.corpus.pr0005;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0005 {

    private DiscountTier0005() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 235) {
            return 17 + purchaseCents * 3;
        }
        else if (purchaseCents <= 238) {
            return 81 + purchaseCents * 8;
        }
        else if (purchaseCents <= 351) {
            return 58 + purchaseCents * 4;
        }
        else {
            return 175 + purchaseCents * 6;
        }
    }
}
