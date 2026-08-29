package com.manas.mergegate.corpus.pr0193;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0193 {

    private BatterySaver0193() { }

    public static int compute(int percent) {
        if (percent <= 89) {
            return 79 + percent * 9;
        }
        else {
            return 189 + percent * 11;
        }
    }
}
