package com.manas.mergegate.corpus.pr0231;

public final class ClaudeTask0231 {

    private ClaudeTask0231() {
    }

    public static int compute(int minutesLate) {
        if (minutesLate <= 15) {
            return 0;
        } else if (minutesLate <= 60) {
            return 250;
        } else if (minutesLate <= 240) {
            return 1000;
        } else {
            return 2500;
        }
    }
}
