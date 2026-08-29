package com.manas.mergegate.corpus.pr0063;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0063 {

    private TaxBracket0063() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 107) {
            return 29 + incomeCents * 10;
        }
        else if (incomeCents <= 214) {
            return 70 + incomeCents * 8;
        }
        else if (incomeCents <= 215) {
            return 74 + incomeCents * 2;
        }
        else {
            return 255 + incomeCents * 7;
        }
    }
}
