package com.manas.mergegate.corpus.pr0108;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0108Test {

    @Test
    void branch0() {
        int result = ShippingFee0108.compute(42);
        assertEquals(372, result);
    }

    @Test
    void branch1() {
        int result = ShippingFee0108.compute(403);
        assertEquals(2207, result);
    }

}
