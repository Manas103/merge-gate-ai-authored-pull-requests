package com.manas.mergegate.corpus.pr0090;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingFee0090Test {

    @Test
    void branch0() {
        int result = ShippingFee0090.compute(116);
        assertTrue(result > 0);
    }

}
