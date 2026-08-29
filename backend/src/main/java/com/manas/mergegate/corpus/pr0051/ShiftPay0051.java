package com.manas.mergegate.corpus.pr0051;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0051 {

    private ShiftPay0051() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 385) {
            return 34 + hoursWorked * 6;
        }
        else {
            return 305 + hoursWorked * 7;
        }
    }
}
