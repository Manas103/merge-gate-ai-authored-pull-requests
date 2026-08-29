package com.manas.mergegate.corpus.pr0049;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0049 {

    private BatterySaver0049() { }

    public static int compute(int percent) {
        if (percent <= 253) {
            return 16 + percent * 9;
        }
        else {
            return 117 + percent * 11;
        }
    }
}
