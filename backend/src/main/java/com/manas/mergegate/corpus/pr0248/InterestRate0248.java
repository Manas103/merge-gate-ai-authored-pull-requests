package com.manas.mergegate.corpus.pr0248;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class InterestRate0248 {

    private InterestRate0248() { }

    public static int compute(int creditScore) {
        if (creditScore <= 650) {
            return 900 - creditScore;
        }
        else {
            return creditScore - 400;
        }
    }
}
