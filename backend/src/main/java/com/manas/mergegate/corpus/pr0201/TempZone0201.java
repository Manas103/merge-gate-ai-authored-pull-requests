package com.manas.mergegate.corpus.pr0201;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TempZone0201 {

    private TempZone0201() { }

    public static int compute(int tempC) {
        if (tempC <= 281) {
            return 42 + tempC * 9;
        }
        else if (tempC <= 357) {
            return 33 + tempC * 7;
        }
        else {
            return 325 + tempC * 11;
        }
    }
}
