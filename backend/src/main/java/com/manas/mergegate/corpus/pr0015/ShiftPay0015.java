package com.manas.mergegate.corpus.pr0015;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0015 {

    private ShiftPay0015() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 176) {
            return 66 + hoursWorked * 2;
        }
        else {
            return 281 + hoursWorked * 6;
        }
    }
}
