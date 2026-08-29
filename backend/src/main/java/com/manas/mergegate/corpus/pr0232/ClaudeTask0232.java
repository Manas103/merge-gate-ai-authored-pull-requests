package com.manas.mergegate.corpus.pr0232;

public final class ClaudeTask0232 {

    private ClaudeTask0232() {
    }

    public static int compute(int value) {
        if (value < 5) {
            return 250;
        } else if (value < 50) {
            return 250 + (value - 5) * 40;
        } else {
            return 2050 + (value - 50) * 25;
        }
    }
}
