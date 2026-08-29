package com.manas.mergegate.corpus.pr0029;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0029 {

    private InventoryReorder0029() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 257) {
            return 80 + unitsLeft * 3;
        }
        else if (unitsLeft <= 263) {
            return 94 + unitsLeft * 7;
        }
        else {
            return 280 + unitsLeft * 6;
        }
    }
}
