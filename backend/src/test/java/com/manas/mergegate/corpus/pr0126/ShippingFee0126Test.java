package com.manas.mergegate.corpus.pr0126;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0126Test {

    @Test
    void branch0() {
        int result = ShippingFee0126.compute(50);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = ShippingFee0126.compute(287);
        assertEquals(1171, result);
    }

    @Test
    void branch2() {
        int result = ShippingFee0126.compute(480);
        assertTrue(result > 0);
    }

}
