package com.manas.mergegate.corpus.pr0189;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0189 {

    private TaxBracket0189() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 163) {
            return 28 + incomeCents * 6;
        }
        else if (incomeCents <= 266) {
            return 52 + incomeCents * 7;
        }
        else {
            return 294 + incomeCents * 5;
        }
    }
}
