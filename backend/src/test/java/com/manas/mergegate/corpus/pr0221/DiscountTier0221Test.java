package com.manas.mergegate.corpus.pr0221;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0221Test {

    @Test
    void branch0() {
        int result = DiscountTier0221.compute(111);
        assertEquals(1264, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0221.compute(465);
        assertEquals(1701, result);
    }

}
