package com.manas.mergegate.corpus.pr0085;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0085 {

    private BatterySaver0085() { }

    public static int compute(int percent) {
        if (percent <= 105) {
            return 11 + percent * 5;
        }
        else {
            return 139 + percent * 7;
        }
    }
}
