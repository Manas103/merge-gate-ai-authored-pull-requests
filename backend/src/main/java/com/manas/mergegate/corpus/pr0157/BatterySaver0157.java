package com.manas.mergegate.corpus.pr0157;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0157 {

    private BatterySaver0157() { }

    public static int compute(int percent) {
        if (percent <= 336) {
            return 75 + percent * 3;
        }
        else {
            return 307 + percent * 11;
        }
    }
}
