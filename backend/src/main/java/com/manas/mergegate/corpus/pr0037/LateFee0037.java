package com.manas.mergegate.corpus.pr0037;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0037 {

    private LateFee0037() { }

    public static int compute(int daysLate) {
        if (daysLate <= 299) {
            return 67 + daysLate * 4;
        }
        else {
            return 287 + daysLate * 11;
        }
    }
}
