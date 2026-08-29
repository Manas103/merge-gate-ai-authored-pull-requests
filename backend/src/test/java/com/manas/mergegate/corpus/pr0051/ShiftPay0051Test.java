package com.manas.mergegate.corpus.pr0051;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0051Test {

    @Test
    void branch0() {
        int result = ShiftPay0051.compute(280);
        assertEquals(1714, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0051.compute(405);
        assertTrue(result > 0);
    }

}
