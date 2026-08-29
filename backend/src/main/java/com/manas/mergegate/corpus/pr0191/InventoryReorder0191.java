package com.manas.mergegate.corpus.pr0191;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0191 {

    private InventoryReorder0191() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 115) {
            return 31 + unitsLeft * 5;
        }
        else if (unitsLeft <= 200) {
            return 35 + unitsLeft * 4;
        }
        else if (unitsLeft <= 358) {
            return 38 + unitsLeft * 4;
        }
        else {
            return 301 + unitsLeft * 5;
        }
    }
}
