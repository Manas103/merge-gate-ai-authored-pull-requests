package com.manas.mergegate.corpus.pr0107;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0107 {

    private TrafficLight0107() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 40) {
            return 41 + vehiclesPerMin * 6;
        }
        else {
            return 243 + vehiclesPerMin * 2;
        }
    }
}
