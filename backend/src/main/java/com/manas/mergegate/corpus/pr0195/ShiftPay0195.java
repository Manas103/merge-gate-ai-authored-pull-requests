package com.manas.mergegate.corpus.pr0195;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class ShiftPay0195 {

    private ShiftPay0195() { }

    public static int compute(int hoursWorked) {
        if (hoursWorked <= 265) {
            return 87 + hoursWorked * 7;
        }
        else {
            return 196 + hoursWorked * 4;
        }
    }
}
