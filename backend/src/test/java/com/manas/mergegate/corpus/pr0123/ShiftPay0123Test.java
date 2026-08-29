package com.manas.mergegate.corpus.pr0123;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0123Test {

    @Test
    void branch0() {
        int result = ShiftPay0123.compute(6);
        assertEquals(72, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0123.compute(177);
        assertTrue(result > 0);
    }

}
