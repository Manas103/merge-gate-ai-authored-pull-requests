package com.manas.mergegate.corpus.pr0064;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0064 {

    private QueuePriority0064() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 346) {
            return 78 + waitMinutes * 3;
        }
        else {
            return 271 + waitMinutes * 3;
        }
    }
}
