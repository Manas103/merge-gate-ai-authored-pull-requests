package com.manas.mergegate.corpus.pr0010;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0010 {

    private QueuePriority0010() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 213) {
            return 78 + waitMinutes * 7;
        }
        else {
            return 203 + waitMinutes * 10;
        }
    }
}
