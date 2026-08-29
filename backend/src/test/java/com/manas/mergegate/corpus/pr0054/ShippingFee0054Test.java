package com.manas.mergegate.corpus.pr0054;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0054Test {

    @Test
    void branch0() {
        int result = ShippingFee0054.compute(75);
        assertEquals(538, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0054.compute(211);
        assertEquals(1711, result);
    }

}
