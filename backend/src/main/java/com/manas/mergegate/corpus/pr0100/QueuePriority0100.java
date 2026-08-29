package com.manas.mergegate.corpus.pr0100;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0100 {

    private QueuePriority0100() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 104) {
            return 20 + waitMinutes * 10;
        }
        else if (waitMinutes <= 311) {
            return 86 + waitMinutes * 10;
        }
        else {
            return 167 + waitMinutes * 3;
        }
    }
}
