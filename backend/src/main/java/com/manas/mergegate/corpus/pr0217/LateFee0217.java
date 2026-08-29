package com.manas.mergegate.corpus.pr0217;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0217 {

    private LateFee0217() { }

    public static int compute(int daysLate) {
        if (daysLate <= 316) {
            return 66 + daysLate * 5;
        }
        else {
            return 157 + daysLate * 9;
        }
    }
}
