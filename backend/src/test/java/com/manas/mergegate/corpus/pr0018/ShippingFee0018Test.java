package com.manas.mergegate.corpus.pr0018;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0018Test {

    @Test
    void branch0() {
        int result = ShippingFee0018.compute(10);
        assertEquals(103, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0018.compute(112);
        assertEquals(579, result);
    }

    @Test
    void branch2() {
        int result = ShippingFee0018.compute(186);
        assertTrue(result > 0);
    }

}
