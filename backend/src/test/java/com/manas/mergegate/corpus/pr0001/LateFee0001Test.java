package com.manas.mergegate.corpus.pr0001;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0001Test {

    @Test
    void branch0() {
        int result = LateFee0001.compute(68);
        assertEquals(793, result);
    }

    @Test
    void branch1() {
        int result = LateFee0001.compute(113);
        assertTrue(result > 0);
    }

}
