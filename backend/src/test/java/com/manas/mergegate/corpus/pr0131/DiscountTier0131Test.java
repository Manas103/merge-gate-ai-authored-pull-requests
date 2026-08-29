package com.manas.mergegate.corpus.pr0131;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0131Test {

    @Test
    void branch0() {
        int result = DiscountTier0131.compute(7);
        assertEquals(108, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0131.compute(27);
        assertEquals(287, result);
    }

    @Test
    void branch2() {
        int result = DiscountTier0131.compute(271);
        assertTrue(result > 0);
    }

}
