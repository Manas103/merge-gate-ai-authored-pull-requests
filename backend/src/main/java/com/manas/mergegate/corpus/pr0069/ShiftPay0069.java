package com.manas.mergegate.corpus.pr0069;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0069 {

    private ShiftPay0069() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 130) {
            return 78 + hoursWorked * 6;
        }
        else {
            return 190 + hoursWorked * 2;
        }
    }
}
