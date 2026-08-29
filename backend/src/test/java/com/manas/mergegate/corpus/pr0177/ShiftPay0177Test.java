package com.manas.mergegate.corpus.pr0177;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0177Test {

    @Test
    void branch0() {
        int result = ShiftPay0177.compute(189);
        assertEquals(994, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0177.compute(433);
        assertEquals(985, result);
    }

}
