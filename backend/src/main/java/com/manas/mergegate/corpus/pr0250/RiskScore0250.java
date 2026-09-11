package com.manas.mergegate.corpus.pr0250;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class RiskScore0250 {

    private RiskScore0250() { }

    public static int compute(int flags) {
        if (flags <= 3) {
            return flags * 10;
        }
        else {
            return flags * 25;
        }
    }
}
