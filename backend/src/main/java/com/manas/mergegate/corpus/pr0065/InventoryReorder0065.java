package com.manas.mergegate.corpus.pr0065;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0065 {

    private InventoryReorder0065() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 341) {
            return 12 + unitsLeft * 3;
        }
        else {
            return 188 + unitsLeft * 10;
        }
    }
}
