package com.manas.mergegate.corpus.pr0011;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0011Test {

    @Test
    void branch0() {
        int result = InventoryReorder0011.compute(94);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0011.compute(183);
        assertEquals(1502, result);
    }

    @Test
    void branch2() {
        int result = InventoryReorder0011.compute(505);
        assertTrue(result > 0);
    }

}
