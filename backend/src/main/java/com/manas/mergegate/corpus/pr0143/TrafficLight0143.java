package com.manas.mergegate.corpus.pr0143;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0143 {

    private TrafficLight0143() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 209) {
            return 15 + vehiclesPerMin * 8;
        }
        else {
            return 140 + vehiclesPerMin * 3;
        }
    }
}
