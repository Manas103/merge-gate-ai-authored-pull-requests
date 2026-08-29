package com.manas.mergegate.corpus.pr0141;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShiftPay0141Test {

    @Test
    void branch0() {
        int result = ShiftPay0141.compute(75);
        assertEquals(409, result);
    }

    @Test
    void branch1() {
        int result = ShiftPay0141.compute(403);
        assertTrue(result > 0);
    }

}
