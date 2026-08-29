package com.manas.mergegate.corpus.pr0139;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0139 {

    private BatterySaver0139() { }

    public static int compute(int percent) {
        if (percent <= 104) {
            return 39 + percent * 9;
        }
        else {
            return 130 + percent * 5;
        }
    }
}
