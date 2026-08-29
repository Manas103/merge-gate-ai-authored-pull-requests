package com.manas.mergegate.corpus.pr0243;

public final class ClaudeTask0243 {

    private ClaudeTask0243() {
    }

    public static int compute(int value) {
        if (value <= 30) {
            return 0;
        } else if (value <= 120) {
            return 250;
        } else if (value <= 480) {
            return 900;
        } else {
            return 1800;
        }
    }
}
