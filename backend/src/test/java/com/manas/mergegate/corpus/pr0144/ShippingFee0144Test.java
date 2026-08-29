package com.manas.mergegate.corpus.pr0144;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0144Test {

    @Test
    void branch0() {
        int result = ShippingFee0144.compute(113);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = ShippingFee0144.compute(373);
        assertEquals(2792, result);
    }

}
