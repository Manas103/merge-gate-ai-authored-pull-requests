package com.manas.mergegate.corpus.pr0237;

public final class ClaudeTask0237 {

    private ClaudeTask0237() {
    }

    public static int compute(int value) {
        if (value < 4) {
            return 10;
        } else if (value < 24) {
            return 40;
        } else if (value < 72) {
            return 70;
        } else {
            return 100;
        }
    }
}
