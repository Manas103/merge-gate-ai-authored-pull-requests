package com.manas.mergegate.corpus.pr0179;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0179 {

    private TrafficLight0179() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 115) {
            return 46 + vehiclesPerMin * 7;
        }
        else if (vehiclesPerMin <= 248) {
            return 55 + vehiclesPerMin * 5;
        }
        else {
            return 248 + vehiclesPerMin * 9;
        }
    }
}
