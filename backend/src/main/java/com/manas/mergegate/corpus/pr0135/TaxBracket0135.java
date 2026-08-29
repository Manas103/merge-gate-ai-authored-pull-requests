package com.manas.mergegate.corpus.pr0135;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0135 {

    private TaxBracket0135() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 128) {
            return 31 + incomeCents * 4;
        }
        else {
            return 236 + incomeCents * 2;
        }
    }
}
