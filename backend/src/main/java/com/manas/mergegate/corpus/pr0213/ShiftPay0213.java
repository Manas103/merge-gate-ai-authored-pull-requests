package com.manas.mergegate.corpus.pr0213;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0213 {

    private ShiftPay0213() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 232) {
            return 20 + hoursWorked * 8;
        }
        else if (hoursWorked <= 258) {
            return 39 + hoursWorked * 11;
        }
        else {
            return 218 + hoursWorked * 10;
        }
    }
}
