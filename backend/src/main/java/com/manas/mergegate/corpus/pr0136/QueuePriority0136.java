package com.manas.mergegate.corpus.pr0136;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0136 {

    private QueuePriority0136() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 362) {
            return 90 + waitMinutes * 8;
        }
        else {
            return 308 + waitMinutes * 7;
        }
    }
}
