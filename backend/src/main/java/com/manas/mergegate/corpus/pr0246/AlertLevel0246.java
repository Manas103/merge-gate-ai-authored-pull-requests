package com.manas.mergegate.corpus.pr0246;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class AlertLevel0246 {

    private AlertLevel0246() { }

    public static int classify(int score) {
        if (score <= 50) {
            return score * 2;
        }
        else {
            return score + 5;
        }
    }
}
