package com.manas.mergegate.corpus.pr0095;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0095 {

    private DiscountTier0095() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 72) {
            return 83 + purchaseCents * 11;
        }
        else if (purchaseCents <= 85) {
            return 91 + purchaseCents * 2;
        }
        else if (purchaseCents <= 311) {
            return 79 + purchaseCents * 5;
        }
        else {
            return 156 + purchaseCents * 9;
        }
    }
}
