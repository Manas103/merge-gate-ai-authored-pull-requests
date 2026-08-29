package com.manas.mergegate.corpus.pr0021;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TempZone0021 {

    private TempZone0021() { }

    public static int compute(int tempC) {
        if (tempC <= 328) {
            return 87 + tempC * 5;
        }
        else if (tempC <= 361) {
            return 84 + tempC * 11;
        }
        else {
            return 161 + tempC * 4;
        }
    }
}
