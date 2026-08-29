package com.manas.mergegate.corpus.pr0238;

public final class ClaudeTask0238 {

    private ClaudeTask0238() {
    }

    public static int compute(int value) {
        if (value < 10) {
            return 1;
        } else if (value < 50) {
            return 5;
        } else if (value < 200) {
            return 8;
        } else {
            return 10;
        }
    }
}
