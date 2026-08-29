package com.manas.mergegate.corpus.pr0185;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0185 {

    private DiscountTier0185() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 42) {
            return 40 + purchaseCents * 5;
        }
        else if (purchaseCents <= 69) {
            return 92 + purchaseCents * 4;
        }
        else {
            return 215 + purchaseCents * 11;
        }
    }
}
