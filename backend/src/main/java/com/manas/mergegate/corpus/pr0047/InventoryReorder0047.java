package com.manas.mergegate.corpus.pr0047;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0047 {

    private InventoryReorder0047() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 171) {
            return 81 + unitsLeft * 2;
        }
        else {
            return 289 + unitsLeft * 2;
        }
    }
}
