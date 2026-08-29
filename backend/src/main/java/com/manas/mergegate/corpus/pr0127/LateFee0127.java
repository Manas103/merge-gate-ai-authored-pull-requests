package com.manas.mergegate.corpus.pr0127;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0127 {

    private LateFee0127() { }

    public static int compute(int daysLate) {
        if (daysLate <= 167) {
            return 73 + daysLate * 6;
        }
        else {
            return 262 + daysLate * 2;
        }
    }
}
