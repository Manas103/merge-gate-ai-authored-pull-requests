package com.manas.mergegate.corpus.pr0139;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0139Test {

    @Test
    void branch0() {
        int result = BatterySaver0139.compute(15);
        assertEquals(174, result);
    }

    @Test
    void branch1() {
        int result = BatterySaver0139.compute(192);
        assertTrue(result > 0);
    }

}
