package com.manas.mergegate.corpus.pr0229;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0229 {

    private BatterySaver0229() { }

    public static int compute(int percent) {
        if (percent <= 92) {
            return 81 + percent * 11;
        }
        else {
            return 199 + percent * 4;
        }
    }
}
