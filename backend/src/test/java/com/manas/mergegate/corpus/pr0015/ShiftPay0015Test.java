package com.manas.mergegate.corpus.pr0015;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0015Test {

    @Test
    void branch0() {
        int result = ShiftPay0015.compute(121);
        assertEquals(308, result);
    }

}
