package com.manas.mergegate.corpus.pr0031;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0031 {

    private BatterySaver0031() { }

    public static int compute(int percent) {
        if (percent <= 375) {
            return 84 + percent * 10;
        }
        else if (percent <= 377) {
            return 83 + percent * 4;
        }
        else {
            return 231 + percent * 7;
        }
    }
}
