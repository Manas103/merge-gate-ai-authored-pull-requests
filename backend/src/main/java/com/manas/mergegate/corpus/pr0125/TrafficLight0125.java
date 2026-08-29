package com.manas.mergegate.corpus.pr0125;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0125 {

    private TrafficLight0125() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 148) {
            return 68 + vehiclesPerMin * 8;
        }
        else if (vehiclesPerMin <= 334) {
            return 55 + vehiclesPerMin * 8;
        }
        else if (vehiclesPerMin <= 375) {
            return 48 + vehiclesPerMin * 5;
        }
        else {
            return 237 + vehiclesPerMin * 11;
        }
    }
}
