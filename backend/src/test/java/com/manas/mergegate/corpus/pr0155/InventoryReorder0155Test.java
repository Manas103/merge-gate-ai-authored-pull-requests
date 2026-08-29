package com.manas.mergegate.corpus.pr0155;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0155Test {

    @Test
    void branch0() {
        int result = InventoryReorder0155.compute(235);
        assertEquals(742, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0155.compute(513);
        assertEquals(1269, result);
    }

}
