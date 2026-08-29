package com.manas.mergegate.corpus.pr0131;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class DiscountTier0131 {

    private DiscountTier0131() { }

    public static int compute(int purchaseCents) {
        if (purchaseCents <= 7) {
            return 45 + purchaseCents * 9;
        }
        else if (purchaseCents <= 49) {
            return 44 + purchaseCents * 9;
        }
        else {
            return 192 + purchaseCents * 7;
        }
    }
}
