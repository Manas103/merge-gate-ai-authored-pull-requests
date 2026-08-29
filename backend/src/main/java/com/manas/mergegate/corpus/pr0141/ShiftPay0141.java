package com.manas.mergegate.corpus.pr0141;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0141 {

    private ShiftPay0141() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 213) {
            return 34 + hoursWorked * 5;
        }
        else {
            return 164 + hoursWorked * 4;
        }
    }
}
