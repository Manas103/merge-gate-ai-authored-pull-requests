package com.manas.mergegate.corpus.pr0209;

/** Generated PR unit. See corpus_manifest.json for provenance. */
public final class InventoryReorder0209 {

    private InventoryReorder0209() { }

    public static int compute(int unitsLeft) {
        if (unitsLeft <= 364) {
            return 16 + unitsLeft * 10;
        }
        else {
            return 231 + unitsLeft * 5;
        }
    }
}
