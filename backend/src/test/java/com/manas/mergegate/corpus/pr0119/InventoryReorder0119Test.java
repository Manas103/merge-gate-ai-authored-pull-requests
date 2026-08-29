package com.manas.mergegate.corpus.pr0119;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0119Test {

    @Test
    void branch0() {
        int result = InventoryReorder0119.compute(107);
        assertEquals(441, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0119.compute(635);
        assertTrue(result > 0);
    }

}
