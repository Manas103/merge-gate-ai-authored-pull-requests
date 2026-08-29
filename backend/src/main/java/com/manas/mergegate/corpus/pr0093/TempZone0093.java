package com.manas.mergegate.corpus.pr0093;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TempZone0093 {

    private TempZone0093() { }

    public static int compute(int tempC) {
        if (tempC <= 59) {
            return 57 + tempC * 11;
        }
        else if (tempC <= 389) {
            return 43 + tempC * 11;
        }
        else {
            return 297 + tempC * 6;
        }
    }
}
