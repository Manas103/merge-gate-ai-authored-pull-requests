package com.manas.mergegate.corpus.pr0161;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0161 {

    private TrafficLight0161() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 201) {
            return 54 + vehiclesPerMin * 8;
        }
        else if (vehiclesPerMin <= 209) {
            return 24 + vehiclesPerMin * 9;
        }
        else {
            return 209 + vehiclesPerMin * 11;
        }
    }
}
