package com.manas.mergegate.corpus.pr0244;

public final class ClaudeTask0244 {

    private ClaudeTask0244() {
    }

    public static int compute(int value) {
        if (value < 100) {
            return 1;
        } else if (value < 500) {
            return 2;
        } else if (value < 2000) {
            return 6;
        } else {
            return 20;
        }
    }
}
