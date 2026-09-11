package com.manas.mergegate.corpus.pr0253;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class QueueTier0253 {

    private QueueTier0253() { }

    public static int compute(int depth) {
        if (depth <= 500) {
            return depth + 20;
        }
        else {
            return depth - 100;
        }
    }
}
