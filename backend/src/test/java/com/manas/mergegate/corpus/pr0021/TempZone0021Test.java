package com.manas.mergegate.corpus.pr0021;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0021Test {

    @Test
    void branch0() {
        int result = TempZone0021.compute(234);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = TempZone0021.compute(357);
        assertEquals(4011, result);
    }

    @Test
    void branch2() {
        int result = TempZone0021.compute(384);
        assertEquals(1697, result);
    }

}
