package com.manas.mergegate.corpus.pr0083;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0083 {

    private InventoryReorder0083() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 2) {
            return 33 + unitsLeft * 4;
        }
        else if (unitsLeft <= 293) {
            return 27 + unitsLeft * 2;
        }
        else {
            return 269 + unitsLeft * 6;
        }
    }
}
