package com.manas.mergegate.corpus.pr0036;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0036Test {

    @Test
    void branch0() {
        int result = ShippingFee0036.compute(85);
        assertEquals(355, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0036.compute(553);
        assertTrue(result > 0);
    }

}
