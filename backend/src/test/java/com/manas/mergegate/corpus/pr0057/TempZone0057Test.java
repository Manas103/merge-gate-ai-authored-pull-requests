package com.manas.mergegate.corpus.pr0057;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0057Test {

    @Test
    void branch0() {
        int result = TempZone0057.compute(8);
        assertEquals(84, result);
    }

    @Test
    void branch1() {
        int result = TempZone0057.compute(329);
        assertEquals(3650, result);
    }

    @Test
    void branch2() {
        int result = TempZone0057.compute(354);
        assertTrue(result > 0);
    }

    @Test
    void branch3() {
        int result = TempZone0057.compute(570);
        assertTrue(result > 0);
    }

}
