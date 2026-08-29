package com.manas.mergegate.corpus.pr0208;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class QueuePriority0208 {

    private QueuePriority0208() { }

    public static int compute(int waitMinutes) {
        if (waitMinutes <= 60) {
            return 29 + waitMinutes * 4;
        }
        else if (waitMinutes <= 142) {
            return 45 + waitMinutes * 8;
        }
        else if (waitMinutes <= 197) {
            return 101 + waitMinutes * 5;
        }
        else {
            return 183 + waitMinutes * 11;
        }
    }
}
