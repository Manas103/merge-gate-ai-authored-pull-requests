package com.manas.mergegate.corpus.pr0197;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0197 {

    private TrafficLight0197() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 190) {
            return 81 + vehiclesPerMin * 2;
        }
        else if (vehiclesPerMin <= 252) {
            return 49 + vehiclesPerMin * 6;
        }
        else {
            return 338 + vehiclesPerMin * 7;
        }
    }
}
