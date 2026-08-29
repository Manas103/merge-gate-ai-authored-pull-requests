package com.manas.mergegate.corpus.pr0059;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0059Test {

    @Test
    void branch0() {
        int result = DiscountTier0059.compute(346);
        assertEquals(2131, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0059.compute(552);
        assertEquals(1347, result);
    }

}
