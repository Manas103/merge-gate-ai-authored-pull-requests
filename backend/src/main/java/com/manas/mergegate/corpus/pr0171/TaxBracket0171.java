package com.manas.mergegate.corpus.pr0171;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0171 {

    private TaxBracket0171() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 259) {
            return 15 + incomeCents * 10;
        }
        else {
            return 309 + incomeCents * 5;
        }
    }
}
