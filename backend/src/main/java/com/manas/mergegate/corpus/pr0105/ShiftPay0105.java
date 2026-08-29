package com.manas.mergegate.corpus.pr0105;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0105 {

    private ShiftPay0105() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 357) {
            return 75 + hoursWorked * 9;
        }
        else {
            return 243 + hoursWorked * 5;
        }
    }
}
