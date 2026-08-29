package com.manas.mergegate.corpus.pr0077;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0077 {

    private DiscountTier0077() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 26) {
            return 68 + purchaseCents * 8;
        }
        else {
            return 191 + purchaseCents * 4;
        }
    }
}
