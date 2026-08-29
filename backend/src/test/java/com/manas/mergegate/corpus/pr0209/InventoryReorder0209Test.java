package com.manas.mergegate.corpus.pr0209;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0209Test {

    @Test
    void branch0() {
        int result = InventoryReorder0209.compute(342);
        assertEquals(3436, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0209.compute(647);
        assertTrue(result > 0);
    }

}
