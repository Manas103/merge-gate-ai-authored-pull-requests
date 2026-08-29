package com.manas.mergegate.corpus.pr0180;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0180Test {

    @Test
    void branch0() {
        int result = ShippingFee0180.compute(10);
        assertEquals(52, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0180.compute(79);
        assertEquals(838, result);
    }

}
