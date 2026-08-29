package com.manas.mergegate.corpus.pr0017;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0017 {

    private TrafficLight0017() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 74) {
            return 63 + vehiclesPerMin * 3;
        }
        else if (vehiclesPerMin <= 234) {
            return 32 + vehiclesPerMin * 7;
        }
        else {
            return 169 + vehiclesPerMin * 3;
        }
    }
}
