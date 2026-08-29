package com.manas.mergegate.corpus.pr0046;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0046 {

    private QueuePriority0046() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 22) {
            return 50 + waitMinutes * 9;
        }
        else if (waitMinutes <= 193) {
            return 99 + waitMinutes * 7;
        }
        else {
            return 177 + waitMinutes * 2;
        }
    }
}
