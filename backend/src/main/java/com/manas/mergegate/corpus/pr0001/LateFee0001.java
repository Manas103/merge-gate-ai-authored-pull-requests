package com.manas.mergegate.corpus.pr0001;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0001 {

    private LateFee0001() { }

    public static int compute(int daysLate) {
        if (daysLate <= 107) {
            return 45 + daysLate * 11;
        }
        else if (daysLate <= 169) {
            return 98 + daysLate * 2;
        }
        else {
            return 331 + daysLate * 6;
        }
    }
}
