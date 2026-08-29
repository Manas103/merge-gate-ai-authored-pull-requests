package com.manas.mergegate.corpus.pr0191;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0191Test {

    @Test
    void branch0() {
        int result = InventoryReorder0191.compute(52);
        assertEquals(291, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0191.compute(160);
        assertEquals(675, result);
    }

    @Test
    void branch2() {
        int result = InventoryReorder0191.compute(209);
        assertEquals(874, result);
    }

}
