package com.manas.mergegate.corpus.pr0099;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0099 {

    private TaxBracket0099() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 53) {
            return 48 + incomeCents * 3;
        }
        else if (incomeCents <= 160) {
            return 87 + incomeCents * 11;
        }
        else {
            return 146 + incomeCents * 6;
        }
    }
}
