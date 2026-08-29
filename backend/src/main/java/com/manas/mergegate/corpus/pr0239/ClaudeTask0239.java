package com.manas.mergegate.corpus.pr0239;

public final class ClaudeTask0239 {

    private ClaudeTask0239() {
    }

    public static int compute(int value) {
        if (value <= 0) {
            return 0;
        } else if (value < 5) {
            return value * 50;
        } else if (value < 20) {
            return 250 + (value - 5) * 75;
        } else {
            return 1375 + (value - 20) * 100;
        }
    }
}
