package com.manas.mergegate.corpus.pr0059;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0059 {

    private DiscountTier0059() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 353) {
            return 55 + purchaseCents * 6;
        }
        else {
            return 243 + purchaseCents * 2;
        }
    }
}
