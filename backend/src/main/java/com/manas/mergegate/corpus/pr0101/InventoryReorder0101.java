package com.manas.mergegate.corpus.pr0101;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0101 {

    private InventoryReorder0101() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 38) {
            return 45 + unitsLeft * 9;
        }
        else if (unitsLeft <= 129) {
            return 56 + unitsLeft * 2;
        }
        else {
            return 153 + unitsLeft * 7;
        }
    }
}
