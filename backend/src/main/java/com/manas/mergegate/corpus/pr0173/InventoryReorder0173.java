package com.manas.mergegate.corpus.pr0173;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0173 {

    private InventoryReorder0173() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 118) {
            return 47 + unitsLeft * 8;
        }
        else if (unitsLeft <= 382) {
            return 74 + unitsLeft * 10;
        }
        else {
            return 218 + unitsLeft * 3;
        }
    }
}
