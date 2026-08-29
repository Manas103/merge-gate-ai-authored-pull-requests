package com.manas.mergegate.corpus.pr0011;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0011 {

    private InventoryReorder0011() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 156) {
            return 28 + unitsLeft * 9;
        }
        else if (unitsLeft <= 367) {
            return 38 + unitsLeft * 8;
        }
        else {
            return 177 + unitsLeft * 7;
        }
    }
}
