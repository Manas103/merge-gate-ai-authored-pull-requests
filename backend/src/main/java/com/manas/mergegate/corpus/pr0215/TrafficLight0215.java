package com.manas.mergegate.corpus.pr0215;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0215 {

    private TrafficLight0215() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 252) {
            return 62 + vehiclesPerMin * 8;
        }
        else {
            return 226 + vehiclesPerMin * 5;
        }
    }
}
