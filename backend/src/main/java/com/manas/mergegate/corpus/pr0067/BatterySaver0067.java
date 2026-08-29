package com.manas.mergegate.corpus.pr0067;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0067 {

    private BatterySaver0067() { }

    public static int compute(int percent) {
        if (percent <= 231) {
            return 15 + percent * 7;
        }
        else {
            return 207 + percent * 8;
        }
    }
}
