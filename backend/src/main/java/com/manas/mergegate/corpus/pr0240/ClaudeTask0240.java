package com.manas.mergegate.corpus.pr0240;

public final class ClaudeTask0240 {

    private ClaudeTask0240() {
    }

    public static int compute(int value) {
        if (value < 0) {
            return 0;
        } else if (value < 150) {
            return 50;
        } else if (value < 450) {
            return 100;
        } else {
            return 0;
        }
    }
}
