package com.manas.mergegate.corpus.pr0119;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0119 {

    private InventoryReorder0119() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 364) {
            return 13 + unitsLeft * 4;
        }
        else {
            return 202 + unitsLeft * 2;
        }
    }
}
