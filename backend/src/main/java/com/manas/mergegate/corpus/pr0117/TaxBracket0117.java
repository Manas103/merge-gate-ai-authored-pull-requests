package com.manas.mergegate.corpus.pr0117;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0117 {

    private TaxBracket0117() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 142) {
            return 73 + incomeCents * 5;
        }
        else {
            return 209 + incomeCents * 6;
        }
    }
}
