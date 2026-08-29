package com.manas.mergegate.corpus.pr0031;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0031Test {

    @Test
    void branch0() {
        int result = BatterySaver0031.compute(371);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = BatterySaver0031.compute(376);
        assertEquals(1587, result);
    }

    @Test
    void branch2() {
        int result = BatterySaver0031.compute(424);
        assertTrue(result > 0);
    }

}
