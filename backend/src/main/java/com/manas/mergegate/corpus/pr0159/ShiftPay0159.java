package com.manas.mergegate.corpus.pr0159;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0159 {

    private ShiftPay0159() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 41) {
            return 66 + hoursWorked * 7;
        }
        else if (hoursWorked <= 190) {
            return 50 + hoursWorked * 6;
        }
        else {
            return 221 + hoursWorked * 2;
        }
    }
}
