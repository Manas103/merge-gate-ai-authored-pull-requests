package com.manas.mergegate.corpus.pr0195;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0195Test {

    @Test
    void branch0() {
        int result = ShiftPay0195.compute(98);
        assertEquals(773, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0195.compute(294);
        assertEquals(1372, result);
    }

}
