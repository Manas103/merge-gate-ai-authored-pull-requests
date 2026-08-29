package com.manas.mergegate.corpus.pr0199;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0199Test {

    @Test
    void branch0() {
        int result = LateFee0199.compute(72);
        assertEquals(497, result);
    }

    @Test
    void branch1() {
        int result = LateFee0199.compute(351);
        assertTrue(result > 0);
    }

}
