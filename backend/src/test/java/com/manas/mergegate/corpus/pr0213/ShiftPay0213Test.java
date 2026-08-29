package com.manas.mergegate.corpus.pr0213;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0213Test {

    @Test
    void branch0() {
        int result = ShiftPay0213.compute(12);
        assertEquals(116, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0213.compute(251);
        assertEquals(2800, result);
    }

    @Test
    void branch2() {
        int result = ShiftPay0213.compute(337);
        assertTrue(result > 0);
    }

}
