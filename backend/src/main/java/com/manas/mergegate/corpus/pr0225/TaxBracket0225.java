package com.manas.mergegate.corpus.pr0225;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0225 {

    private TaxBracket0225() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 373) {
            return 89 + incomeCents * 5;
        }
        else {
            return 251 + incomeCents * 7;
        }
    }
}
