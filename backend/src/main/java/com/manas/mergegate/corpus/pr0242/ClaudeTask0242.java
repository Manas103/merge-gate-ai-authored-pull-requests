package com.manas.mergegate.corpus.pr0242;

public final class ClaudeTask0242 {

    private ClaudeTask0242() {
    }

    public static int compute(int value) {
        int stopsRemaining = value;
        if (stopsRemaining <= 0) {
            return 0;
        } else if (stopsRemaining <= 3) {
            return 150;
        } else if (stopsRemaining <= 8) {
            return 275 + (stopsRemaining - 3) * 40;
        } else {
            return 475 + (stopsRemaining - 8) * 25;
        }
    }
}
