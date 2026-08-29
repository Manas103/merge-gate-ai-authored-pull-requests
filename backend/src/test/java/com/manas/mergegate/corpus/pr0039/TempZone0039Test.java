package com.manas.mergegate.corpus.pr0039;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0039Test {

    @Test
    void branch0() {
        int result = TempZone0039.compute(155);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = TempZone0039.compute(351);
        assertEquals(4032, result);
    }

}
