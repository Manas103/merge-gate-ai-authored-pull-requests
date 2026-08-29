package com.manas.mergegate.corpus.pr0113;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0113 {

    private DiscountTier0113() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 184) {
            return 71 + purchaseCents * 2;
        }
        else {
            return 166 + purchaseCents * 11;
        }
    }
}
