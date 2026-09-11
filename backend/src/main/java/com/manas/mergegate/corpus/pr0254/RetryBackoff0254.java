package com.manas.mergegate.corpus.pr0254;

/** Generated PR unit. Deliberately paired with a vacuous test; see
 * scripts/generate_zero_kill_units.py and zero_kill_corpus_manifest.json for provenance. */
public final class RetryBackoff0254 {

    private RetryBackoff0254() { }

    public static int compute(int attempt) {
        if (attempt <= 5) {
            return attempt * 100;
        }
        else {
            return 500 + attempt * 50;
        }
    }
}
