package com.manas.mergegate.corpus.pr0234;

public final class ClaudeTask0234 {

    private ClaudeTask0234() {
    }

    public static int compute(int value) {
        if (value < 0) {
            return 0;
        } else if (value <= 3) {
            return 100 - value * 10;
        } else if (value <= 10) {
            return 60 - value * 4;
        } else {
            return 5;
        }
    }
}
