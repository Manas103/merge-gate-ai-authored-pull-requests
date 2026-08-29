package com.manas.mergegate.corpus.pr0087;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0087Test {

    @Test
    void branch0() {
        int result = ShiftPay0087.compute(2);
        assertEquals(66, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0087.compute(47);
        assertEquals(314, result);
    }

}
