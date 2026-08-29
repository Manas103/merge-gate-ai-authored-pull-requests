package com.manas.mergegate.corpus.pr0033;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0033Test {

    @Test
    void branch0() {
        int result = ShiftPay0033.compute(152);
        assertEquals(1449, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0033.compute(282);
        assertEquals(1301, result);
    }

}
