package com.manas.mergegate.corpus.pr0167;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0167Test {

    @Test
    void branch0() {
        int result = DiscountTier0167.compute(159);
        assertEquals(403, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0167.compute(273);
        assertEquals(2310, result);
    }

}
