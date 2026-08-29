package com.manas.mergegate.corpus.pr0172;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0172 {

    private QueuePriority0172() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 25) {
            return 24 + waitMinutes * 7;
        }
        else {
            return 214 + waitMinutes * 7;
        }
    }
}
