package com.manas.mergegate.corpus.pr0053;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class TrafficLight0053 {

    private TrafficLight0053() { }

    public static int compute(int vehiclesPerMin) {
        if (vehiclesPerMin <= 358) {
            return 47 + vehiclesPerMin * 6;
        }
        else {
            return 187 + vehiclesPerMin * 4;
        }
    }
}
