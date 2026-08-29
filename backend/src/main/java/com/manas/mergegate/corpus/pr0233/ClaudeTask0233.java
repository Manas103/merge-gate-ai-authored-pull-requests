package com.manas.mergegate.corpus.pr0233;

public final class ClaudeTask0233 {

    private ClaudeTask0233() {
    }

    public static int compute(int value) {
        if (value < 0) {
            return 0;
        } else if (value < 5) {
            return value * 10;
        } else if (value < 20) {
            return 50 + (value - 5) * 15;
        } else {
            return 275 + (value - 20) * 25;
        }
    }
}
