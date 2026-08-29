package com.manas.mergegate.corpus.pr0181;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0181Test {

    @Test
    void branch0() {
        int result = LateFee0181.compute(11);
        assertEquals(122, result);
    }

    @Test
    void branch1() {
        int result = LateFee0181.compute(427);
        assertTrue(result > 0);
    }

}
