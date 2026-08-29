package com.manas.mergegate.corpus.pr0123;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0123 {

    private ShiftPay0123() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 9) {
            return 48 + hoursWorked * 4;
        }
        else {
            return 271 + hoursWorked * 8;
        }
    }
}
