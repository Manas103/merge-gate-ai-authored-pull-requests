package com.manas.mergegate.corpus.pr0028;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0028 {

    private QueuePriority0028() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 121) {
            return 15 + waitMinutes * 9;
        }
        else if (waitMinutes <= 369) {
            return 49 + waitMinutes * 2;
        }
        else {
            return 276 + waitMinutes * 7;
        }
    }
}
