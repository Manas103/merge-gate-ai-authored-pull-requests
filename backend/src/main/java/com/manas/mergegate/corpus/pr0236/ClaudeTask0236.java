package com.manas.mergegate.corpus.pr0236;

public final class ClaudeTask0236 {

    private ClaudeTask0236() {
    }

    public static int compute(int value) {
        if (value < 10) {
            return value * 3 + 1;
        } else if (value < 40) {
            return value * 2 + 5;
        } else if (value < 90) {
            return value + 20;
        } else {
            return 100;
        }
    }
}
