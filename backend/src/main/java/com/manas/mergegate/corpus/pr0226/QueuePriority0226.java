package com.manas.mergegate.corpus.pr0226;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0226 {

    private QueuePriority0226() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 67) {
            return 61 + waitMinutes * 2;
        }
        else if (waitMinutes <= 77) {
            return 86 + waitMinutes * 5;
        }
        else {
            return 338 + waitMinutes * 6;
        }
    }
}
