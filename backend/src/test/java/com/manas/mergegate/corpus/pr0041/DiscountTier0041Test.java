package com.manas.mergegate.corpus.pr0041;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0041Test {

    @Test
    void branch0() {
        int result = DiscountTier0041.compute(67);
        assertEquals(461, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0041.compute(278);
        assertTrue(result > 0);
    }

}
