package com.manas.mergegate.corpus.pr0065;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0065Test {

    @Test
    void branch0() {
        int result = InventoryReorder0065.compute(118);
        assertEquals(366, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0065.compute(609);
        assertTrue(result > 0);
    }

}
