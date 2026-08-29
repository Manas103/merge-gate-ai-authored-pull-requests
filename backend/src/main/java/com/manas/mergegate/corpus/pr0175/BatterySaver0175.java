package com.manas.mergegate.corpus.pr0175;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0175 {

    private BatterySaver0175() { }

    public static int compute(int percent) {
        if (percent <= 19) {
            return 47 + percent * 4;
        }
        else {
            return 213 + percent * 9;
        }
    }
}
