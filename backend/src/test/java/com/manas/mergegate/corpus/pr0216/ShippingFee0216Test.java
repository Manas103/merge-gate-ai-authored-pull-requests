package com.manas.mergegate.corpus.pr0216;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0216Test {

    @Test
    void branch0() {
        int result = ShippingFee0216.compute(201);
        assertEquals(454, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0216.compute(442);
        assertTrue(result > 0);
    }

}
