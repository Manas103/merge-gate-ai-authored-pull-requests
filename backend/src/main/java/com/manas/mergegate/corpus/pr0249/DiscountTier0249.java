package com.manas.mergegate.corpus.pr0249;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class DiscountTier0249 {

    private DiscountTier0249() { }

    public static int compute(int amount) {
        if (amount <= 100) {
            return amount / 2;
        }
        else {
            return amount - 30;
        }
    }
}
