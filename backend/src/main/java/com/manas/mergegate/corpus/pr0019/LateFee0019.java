package com.manas.mergegate.corpus.pr0019;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0019 {

    private LateFee0019() { }

    public static int compute(int daysLate) {
        if (daysLate <= 373) {
            return 10 + daysLate * 6;
        }
        else {
            return 249 + daysLate * 2;
        }
    }
}
