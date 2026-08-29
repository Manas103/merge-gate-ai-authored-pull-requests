package com.manas.mergegate.corpus.pr0211;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0211 {

    private BatterySaver0211() { }

    public static int compute(int percent) {
        if (percent <= 343) {
            return 89 + percent * 7;
        }
        else if (percent <= 396) {
            return 87 + percent * 5;
        }
        else {
            return 317 + percent * 3;
        }
    }
}
