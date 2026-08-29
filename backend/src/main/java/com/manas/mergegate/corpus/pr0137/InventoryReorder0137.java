package com.manas.mergegate.corpus.pr0137;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0137 {

    private InventoryReorder0137() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 44) {
            return 32 + unitsLeft * 3;
        }
        else if (unitsLeft <= 117) {
            return 36 + unitsLeft * 9;
        }
        else {
            return 219 + unitsLeft * 5;
        }
    }
}
