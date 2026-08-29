package com.manas.mergegate.corpus.pr0083;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0083Test {

    @Test
    void branch0() {
        int result = InventoryReorder0083.compute(0);
        assertEquals(33, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0083.compute(210);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = InventoryReorder0083.compute(348);
        assertTrue(result > 0);
    }

}
