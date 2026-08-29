package com.manas.mergegate.corpus.pr0190;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0190 {

    private QueuePriority0190() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 298) {
            return 37 + waitMinutes * 4;
        }
        else {
            return 153 + waitMinutes * 10;
        }
    }
}
