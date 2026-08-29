package com.manas.mergegate.corpus.pr0198;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0198Test {

    @Test
    void branch0() {
        int result = ShippingFee0198.compute(147);
        assertEquals(1685, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0198.compute(362);
        assertTrue(result > 0);
    }

}
