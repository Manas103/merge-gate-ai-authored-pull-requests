package com.manas.mergegate.corpus.pr0149;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0149Test {

    @Test
    void branch0() {
        int result = DiscountTier0149.compute(14);
        assertEquals(162, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0149.compute(18);
        assertEquals(317, result);
    }

}
