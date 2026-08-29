package com.manas.mergegate.corpus.pr0072;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0072Test {

    @Test
    void branch0() {
        int result = ShippingFee0072.compute(49);
        assertEquals(411, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0072.compute(179);
        assertEquals(1892, result);
    }

    @Test
    void branch2() {
        int result = ShippingFee0072.compute(469);
        assertTrue(result > 0);
    }

}
