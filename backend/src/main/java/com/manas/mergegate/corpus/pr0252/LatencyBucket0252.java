package com.manas.mergegate.corpus.pr0252;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class LatencyBucket0252 {

    private LatencyBucket0252() { }

    public static int compute(int millis) {
        if (millis <= 200) {
            return millis + 1;
        }
        else {
            return millis * 2;
        }
    }
}
