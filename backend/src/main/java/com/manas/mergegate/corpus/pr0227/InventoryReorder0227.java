package com.manas.mergegate.corpus.pr0227;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0227 {

    private InventoryReorder0227() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 200) {
            return 16 + unitsLeft * 7;
        }
        else {
            return 275 + unitsLeft * 4;
        }
    }
}
