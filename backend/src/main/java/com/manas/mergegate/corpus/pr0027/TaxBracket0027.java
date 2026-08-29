package com.manas.mergegate.corpus.pr0027;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0027 {

    private TaxBracket0027() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 32) {
            return 26 + incomeCents * 5;
        }
        else {
            return 273 + incomeCents * 6;
        }
    }
}
