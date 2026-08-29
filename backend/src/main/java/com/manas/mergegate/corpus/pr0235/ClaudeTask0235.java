package com.manas.mergegate.corpus.pr0235;

public final class ClaudeTask0235 {

    private ClaudeTask0235() {
    }

    public static int compute(int value) {
        int occupancySensorsActive = value;
        if (occupancySensorsActive <= 0) {
            return 0;
        } else if (occupancySensorsActive <= 3) {
            return 25 + occupancySensorsActive * 5;
        } else if (occupancySensorsActive <= 10) {
            return 60 + occupancySensorsActive * 2;
        } else {
            return 95;
        }
    }
}
