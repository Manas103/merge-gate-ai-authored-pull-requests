package com.manas.mergegate.corpus.pr0055;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0055 {

    private LateFee0055() { }

    public static int compute(int daysLate) {
        if (daysLate <= 206) {
            return 23 + daysLate * 2;
        }
        else {
            return 302 + daysLate * 11;
        }
    }
}
