package com.manas.mergegate.corpus.pr0162;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0162Test {

    @Test
    void branch0() {
        int result = ShippingFee0162.compute(10);
        assertEquals(91, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0162.compute(77);
        assertEquals(563, result);
    }

}
