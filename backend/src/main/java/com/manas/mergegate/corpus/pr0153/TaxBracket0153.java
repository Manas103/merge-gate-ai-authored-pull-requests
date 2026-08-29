package com.manas.mergegate.corpus.pr0153;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0153 {

    private TaxBracket0153() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 23) {
            return 88 + incomeCents * 5;
        }
        else {
            return 208 + incomeCents * 3;
        }
    }
}
