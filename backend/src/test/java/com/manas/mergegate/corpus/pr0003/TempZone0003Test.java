package com.manas.mergegate.corpus.pr0003;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0003Test {

    @Test
    void branch0() {
        int result = TempZone0003.compute(19);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = TempZone0003.compute(333);
        assertEquals(1266, result);
    }

}
