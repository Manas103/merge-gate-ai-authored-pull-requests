package com.manas.mergegate.corpus.pr0103;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class BatterySaver0103 {

    private BatterySaver0103() { }

    public static int compute(int percent) {
        if (percent <= 208) {
            return 71 + percent * 8;
        }
        else {
            return 251 + percent * 8;
        }
    }
}
