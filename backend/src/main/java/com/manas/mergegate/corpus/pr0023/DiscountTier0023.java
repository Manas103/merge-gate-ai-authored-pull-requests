package com.manas.mergegate.corpus.pr0023;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0023 {

    private DiscountTier0023() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 243) {
            return 79 + purchaseCents * 10;
        }
        else {
            return 263 + purchaseCents * 4;
        }
    }
}
