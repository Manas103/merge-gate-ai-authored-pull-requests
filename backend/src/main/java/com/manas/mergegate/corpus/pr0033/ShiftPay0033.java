package com.manas.mergegate.corpus.pr0033;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0033 {

    private ShiftPay0033() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 206) {
            return 81 + hoursWorked * 9;
        }
        else {
            return 173 + hoursWorked * 4;
        }
    }
}
