package com.manas.mergegate.corpus.pr0159;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0159Test {

    @Test
    void branch0() {
        int result = ShiftPay0159.compute(22);
        assertEquals(220, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0159.compute(187);
        assertEquals(1172, result);
    }

}
