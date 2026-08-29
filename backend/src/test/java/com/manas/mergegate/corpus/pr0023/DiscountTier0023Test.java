package com.manas.mergegate.corpus.pr0023;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0023Test {

    @Test
    void branch0() {
        int result = DiscountTier0023.compute(54);
        assertEquals(619, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0023.compute(541);
        assertTrue(result > 0);
    }

}
