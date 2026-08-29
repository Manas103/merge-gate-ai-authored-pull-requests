package com.manas.mergegate.corpus.pr0199;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0199 {

    private LateFee0199() { }

    public static int compute(int daysLate) {
        if (daysLate <= 236) {
            return 65 + daysLate * 6;
        }
        else {
            return 230 + daysLate * 9;
        }
    }
}
