package com.manas.mergegate.corpus.pr0247;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class ShippingCost0247 {

    private ShippingCost0247() { }

    public static int compute(int weight) {
        if (weight <= 20) {
            return weight * 3 + 4;
        }
        else {
            return weight * 2 + 50;
        }
    }
}
