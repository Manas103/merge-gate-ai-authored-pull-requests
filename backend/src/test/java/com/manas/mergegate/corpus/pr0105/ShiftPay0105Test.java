package com.manas.mergegate.corpus.pr0105;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0105Test {

    @Test
    void branch0() {
        int result = ShiftPay0105.compute(195);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = ShiftPay0105.compute(428);
        assertEquals(2383, result);
    }

}
