package com.manas.mergegate.corpus.pr0093;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0093Test {

    @Test
    void branch0() {
        int result = TempZone0093.compute(54);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = TempZone0093.compute(435);
        assertEquals(2907, result);
    }

}
