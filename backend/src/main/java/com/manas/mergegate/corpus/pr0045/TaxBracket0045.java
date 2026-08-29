package com.manas.mergegate.corpus.pr0045;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0045 {

    private TaxBracket0045() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 5) {
            return 20 + incomeCents * 2;
        }
        else {
            return 296 + incomeCents * 2;
        }
    }
}
