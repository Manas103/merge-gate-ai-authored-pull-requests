package com.manas.mergegate.corpus.pr0251;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class CreditLimit0251 {

    private CreditLimit0251() { }

    public static int compute(int income) {
        if (income <= 40000) {
            return income / 10;
        }
        else {
            return income / 6;
        }
    }
}
