package com.manas.mergegate.corpus.pr0095;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0095Test {

    @Test
    void branch0() {
        int result = DiscountTier0095.compute(67);
        assertEquals(820, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0095.compute(74);
        assertEquals(239, result);
    }

    @Test
    void branch2() {
        int result = DiscountTier0095.compute(107);
        assertEquals(614, result);
    }

}
