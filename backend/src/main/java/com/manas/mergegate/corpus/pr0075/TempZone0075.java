package com.manas.mergegate.corpus.pr0075;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TempZone0075 {

    private TempZone0075() { }

    public static int compute(int tempC) {
        if (tempC <= 61) {
            return 81 + tempC * 7;
        }
        else if (tempC <= 312) {
            return 76 + tempC * 6;
        }
        else {
            return 240 + tempC * 3;
        }
    }
}
