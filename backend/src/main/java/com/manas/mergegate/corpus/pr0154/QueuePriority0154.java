package com.manas.mergegate.corpus.pr0154;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0154 {

    private QueuePriority0154() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 62) {
            return 67 + waitMinutes * 4;
        }
        else {
            return 186 + waitMinutes * 5;
        }
    }
}
