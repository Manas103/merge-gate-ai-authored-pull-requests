package com.manas.mergegate.corpus.pr0155;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0155 {

    private InventoryReorder0155() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 259) {
            return 37 + unitsLeft * 3;
        }
        else {
            return 243 + unitsLeft * 2;
        }
    }
}
