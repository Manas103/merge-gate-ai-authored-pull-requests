package com.manas.mergegate.corpus.pr0082;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0082 {

    private QueuePriority0082() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 4) {
            return 59 + waitMinutes * 3;
        }
        else {
            return 146 + waitMinutes * 6;
        }
    }
}
