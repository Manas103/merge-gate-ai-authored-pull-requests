package com.manas.mergegate.corpus.pr0181;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class LateFee0181 {

    private LateFee0181() { }

    public static int compute(int daysLate) {
        if (daysLate <= 360) {
            return 78 + daysLate * 4;
        }
        else {
            return 238 + daysLate * 9;
        }
    }
}
