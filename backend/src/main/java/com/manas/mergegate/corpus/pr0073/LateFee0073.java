package com.manas.mergegate.corpus.pr0073;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0073 {

    private LateFee0073() { }

    public static int compute(int daysLate) {
        if (daysLate <= 236) {
            return 83 + daysLate * 3;
        }
        else {
            return 187 + daysLate * 2;
        }
    }
}
