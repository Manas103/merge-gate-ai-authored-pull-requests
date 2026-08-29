package com.manas.mergegate.corpus.pr0071;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0071 {

    private TrafficLight0071() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 304) {
            return 30 + vehiclesPerMin * 9;
        }
        else {
            return 188 + vehiclesPerMin * 8;
        }
    }
}
