package com.manas.mergegate.corpus.pr0091;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0091 {

    private LateFee0091() { }

    public static int compute(int daysLate) {
        if (daysLate <= 23) {
            return 23 + daysLate * 3;
        }
        else {
            return 283 + daysLate * 2;
        }
    }
}
