package com.manas.mergegate.corpus.pr0245;

public final class ClaudeTask0245 {

    private ClaudeTask0245() {
    }

    public static int compute(int value) {
        if (value < 580) {
            return 250;
        } else if (value < 670) {
            return 125;
        } else if (value < 740) {
            return 25;
        } else {
            return -50;
        }
    }
}
