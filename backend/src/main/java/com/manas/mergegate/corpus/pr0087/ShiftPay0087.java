package com.manas.mergegate.corpus.pr0087;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0087 {

    private ShiftPay0087() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 32) {
            return 60 + hoursWorked * 3;
        }
        else {
            return 173 + hoursWorked * 3;
        }
    }
}
