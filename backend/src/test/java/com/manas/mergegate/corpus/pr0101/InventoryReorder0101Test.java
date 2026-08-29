package com.manas.mergegate.corpus.pr0101;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0101Test {

    @Test
    void branch0() {
        int result = InventoryReorder0101.compute(27);
        assertEquals(288, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0101.compute(86);
        assertEquals(228, result);
    }

    @Test
    void branch2() {
        int result = InventoryReorder0101.compute(149);
        assertTrue(result > 0);
    }

}
