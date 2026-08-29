package com.manas.mergegate.corpus.pr0057;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TempZone0057 {

    private TempZone0057() { }

    public static int compute(int tempC) {
        if (tempC <= 13) {
            return 52 + tempC * 4;
        }
        else if (tempC <= 348) {
            return 31 + tempC * 11;
        }
        else if (tempC <= 369) {
            return 77 + tempC * 11;
        }
        else {
            return 336 + tempC * 9;
        }
    }
}
