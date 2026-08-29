package com.manas.mergegate.corpus.pr0137;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0137Test {

    @Test
    void branch0() {
        int result = InventoryReorder0137.compute(34);
        assertEquals(134, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0137.compute(87);
        assertEquals(819, result);
    }

    @Test
    void branch2() {
        int result = InventoryReorder0137.compute(275);
        assertEquals(1594, result);
    }

}
