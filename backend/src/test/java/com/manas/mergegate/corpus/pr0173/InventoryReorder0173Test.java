package com.manas.mergegate.corpus.pr0173;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0173Test {

    @Test
    void branch0() {
        int result = InventoryReorder0173.compute(88);
        assertEquals(751, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0173.compute(309);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = InventoryReorder0173.compute(451);
        assertTrue(result > 0);
    }

}
