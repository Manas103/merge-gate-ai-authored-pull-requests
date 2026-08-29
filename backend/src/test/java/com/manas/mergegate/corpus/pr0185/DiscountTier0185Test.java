package com.manas.mergegate.corpus.pr0185;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0185Test {

    @Test
    void branch0() {
        int result = DiscountTier0185.compute(9);
        assertEquals(85, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0185.compute(51);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = DiscountTier0185.compute(216);
        assertEquals(2591, result);
    }

}
