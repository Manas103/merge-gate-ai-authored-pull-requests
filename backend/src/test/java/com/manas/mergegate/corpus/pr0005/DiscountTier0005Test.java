package com.manas.mergegate.corpus.pr0005;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0005Test {

    @Test
    void branch0() {
        int result = DiscountTier0005.compute(134);
        assertEquals(419, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0005.compute(238);
        assertEquals(1985, result);
    }

    @Test
    void branch2() {
        int result = DiscountTier0005.compute(323);
        assertEquals(1350, result);
    }

    @Test
    void branch3() {
        int result = DiscountTier0005.compute(389);
        assertEquals(2509, result);
    }

}
