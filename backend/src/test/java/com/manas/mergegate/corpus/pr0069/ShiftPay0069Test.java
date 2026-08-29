package com.manas.mergegate.corpus.pr0069;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0069Test {

    @Test
    void branch0() {
        int result = ShiftPay0069.compute(42);
        assertEquals(330, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0069.compute(348);
        assertTrue(result > 0);
    }

}
