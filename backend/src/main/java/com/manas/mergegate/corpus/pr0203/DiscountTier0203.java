package com.manas.mergegate.corpus.pr0203;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0203 {

    private DiscountTier0203() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 271) {
            return 32 + purchaseCents * 6;
        }
        else if (purchaseCents <= 309) {
            return 52 + purchaseCents * 2;
        }
        else {
            return 214 + purchaseCents * 6;
        }
    }
}
