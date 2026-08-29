package com.manas.mergegate.corpus.pr0113;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTier0113Test {

    @Test
    void branch0() {
        int result = DiscountTier0113.compute(55);
        assertEquals(181, result);
    }

    @Test
    void branch1() {
        int result = DiscountTier0113.compute(318);
        assertEquals(3664, result);
    }

}
