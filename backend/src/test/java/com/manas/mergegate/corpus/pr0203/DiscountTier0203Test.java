package com.manas.mergegate.corpus.pr0203;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0203Test {

    @Test
    void branch0() {
        int result = DiscountTier0203.compute(66);
        assertEquals(428, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0203.compute(294);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = DiscountTier0203.compute(377);
        assertTrue(result > 0);
    }

}
