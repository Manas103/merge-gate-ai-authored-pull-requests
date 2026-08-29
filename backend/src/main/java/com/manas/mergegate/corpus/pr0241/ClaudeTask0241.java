package com.manas.mergegate.corpus.pr0241;

public final class ClaudeTask0241 {

    private ClaudeTask0241() {
    }

    public static int compute(int failedLoginAttempts) {
        if (failedLoginAttempts <= 0) {
            return 0;
        } else if (failedLoginAttempts <= 3) {
            return 25;
        } else if (failedLoginAttempts <= 9) {
            return 60;
        } else {
            return 100;
        }
    }
}
