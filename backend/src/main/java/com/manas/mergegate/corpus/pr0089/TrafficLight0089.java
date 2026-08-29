package com.manas.mergegate.corpus.pr0089;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0089 {

    private TrafficLight0089() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 345) {
            return 75 + vehiclesPerMin * 7;
        }
        else {
            return 153 + vehiclesPerMin * 7;
        }
    }
}
