package com.manas.mergegate.corpus.pr0077;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0077Test {

    @Test
    void branch0() {
        int result = DiscountTier0077.compute(13);
        assertEquals(172, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0077.compute(71);
        assertTrue(result > 0);
    }

}
