package com.manas.mergegate.corpus.pr0121;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0121 {

    private BatterySaver0121() { }

    public static int compute(int percent) {
        if (percent <= 295) {
            return 51 + percent * 4;
        }
        else {
            return 215 + percent * 8;
        }
    }
}
