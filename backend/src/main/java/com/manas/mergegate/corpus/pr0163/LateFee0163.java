package com.manas.mergegate.corpus.pr0163;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0163 {

    private LateFee0163() { }

    public static int compute(int daysLate) {
        if (daysLate <= 96) {
            return 58 + daysLate * 11;
        }
        else {
            return 213 + daysLate * 8;
        }
    }
}
