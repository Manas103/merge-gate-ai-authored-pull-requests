package com.manas.mergegate.corpus.pr0029;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0029Test {

    @Test
    void branch0() {
        int result = InventoryReorder0029.compute(56);
        assertEquals(248, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0029.compute(259);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = InventoryReorder0029.compute(296);
        assertEquals(2056, result);
    }

}
