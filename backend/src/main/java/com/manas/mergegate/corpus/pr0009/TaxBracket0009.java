package com.manas.mergegate.corpus.pr0009;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0009 {

    private TaxBracket0009() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 266) {
            return 27 + incomeCents * 6;
        }
        else if (incomeCents <= 341) {
            return 66 + incomeCents * 10;
        }
        else {
            return 266 + incomeCents * 11;
        }
    }
}
