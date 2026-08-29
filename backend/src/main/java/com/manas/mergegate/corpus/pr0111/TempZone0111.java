package com.manas.mergegate.corpus.pr0111;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TempZone0111 {

    private TempZone0111() { }

    public static int compute(int tempC) {
        if (tempC <= 243) {
            return 64 + tempC * 9;
        }
        else if (tempC <= 311) {
            return 83 + tempC * 7;
        }
        else {
            return 140 + tempC * 8;
        }
    }
}
