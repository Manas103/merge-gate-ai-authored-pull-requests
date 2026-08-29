package com.manas.mergegate.corpus.pr0109;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0109 {

    private LateFee0109() { }

    public static int compute(int daysLate) {
        if (daysLate <= 298) {
            return 73 + daysLate * 6;
        }
        else {
            return 260 + daysLate * 4;
        }
    }
}
