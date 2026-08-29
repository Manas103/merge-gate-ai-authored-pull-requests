package com.manas.mergegate.corpus.pr0217;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0217Test {

    @Test
    void branch0() {
        int result = LateFee0217.compute(95);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = LateFee0217.compute(388);
        assertEquals(3649, result);
    }

}
