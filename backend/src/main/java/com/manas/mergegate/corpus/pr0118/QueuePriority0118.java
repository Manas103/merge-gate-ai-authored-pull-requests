package com.manas.mergegate.corpus.pr0118;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0118 {

    private QueuePriority0118() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 208) {
            return 27 + waitMinutes * 2;
        }
        else {
            return 220 + waitMinutes * 2;
        }
    }
}
