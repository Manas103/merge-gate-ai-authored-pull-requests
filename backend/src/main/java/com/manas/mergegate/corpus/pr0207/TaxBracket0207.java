package com.manas.mergegate.corpus.pr0207;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TaxBracket0207 {

    private TaxBracket0207() { }

    public static int compute(int incomeCents) {
        if (incomeCents <= 139) {
            return 57 + incomeCents * 7;
        }
        else if (incomeCents <= 355) {
            return 63 + incomeCents * 3;
        }
        else {
            return 306 + incomeCents * 8;
        }
    }
}
