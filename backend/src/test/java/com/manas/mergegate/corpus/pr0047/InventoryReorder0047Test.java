package com.manas.mergegate.corpus.pr0047;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0047Test {

    @Test
    void branch0() {
        int result = InventoryReorder0047.compute(48);
        assertEquals(177, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0047.compute(394);
        assertTrue(result > 0);
    }

}
