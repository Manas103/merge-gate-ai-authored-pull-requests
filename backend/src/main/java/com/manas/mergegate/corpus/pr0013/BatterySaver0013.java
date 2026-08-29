package com.manas.mergegate.corpus.pr0013;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0013 {

    private BatterySaver0013() { }

    public static int compute(int percent) {
        if (percent <= 175) {
            return 83 + percent * 10;
        }
        else if (percent <= 241) {
            return 77 + percent * 10;
        }
        else {
            return 190 + percent * 10;
        }
    }
}
