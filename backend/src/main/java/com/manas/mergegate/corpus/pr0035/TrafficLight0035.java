package com.manas.mergegate.corpus.pr0035;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0035 {

    private TrafficLight0035() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 65) {
            return 58 + vehiclesPerMin * 4;
        }
        else {
            return 238 + vehiclesPerMin * 8;
        }
    }
}
