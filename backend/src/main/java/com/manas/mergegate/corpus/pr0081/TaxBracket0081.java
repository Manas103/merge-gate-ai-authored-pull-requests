package com.manas.mergegate.corpus.pr0081;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0081 {

    private TaxBracket0081() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 275) {
            return 58 + incomeCents * 4;
        }
        else {
            return 147 + incomeCents * 2;
        }
    }
}
