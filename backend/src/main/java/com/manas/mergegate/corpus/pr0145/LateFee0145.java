package com.manas.mergegate.corpus.pr0145;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0145 {

    private LateFee0145() { }

    public static int compute(int daysLate) {
        if (daysLate <= 8) {
            return 64 + daysLate * 11;
        }
        else if (daysLate <= 311) {
            return 63 + daysLate * 4;
        }
        else {
            return 182 + daysLate * 6;
        }
    }
}
