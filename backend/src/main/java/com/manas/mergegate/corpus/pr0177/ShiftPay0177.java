package com.manas.mergegate.corpus.pr0177;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0177 {

    private ShiftPay0177() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 288) {
            return 49 + hoursWorked * 5;
        }
        else {
            return 119 + hoursWorked * 2;
        }
    }
}
