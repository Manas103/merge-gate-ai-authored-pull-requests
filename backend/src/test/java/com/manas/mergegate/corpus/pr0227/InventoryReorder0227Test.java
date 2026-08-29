package com.manas.mergegate.corpus.pr0227;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryReorder0227Test {

    @Test
    void branch0() {
        int result = InventoryReorder0227.compute(164);
        assertEquals(1164, result);
    }

    @Test
    void branch1() {
        int result = InventoryReorder0227.compute(295);
        assertEquals(1455, result);
    }

}
