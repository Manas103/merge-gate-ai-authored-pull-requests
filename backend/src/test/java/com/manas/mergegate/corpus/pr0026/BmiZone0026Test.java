package com.manas.mergegate.corpus.pr0026;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0026Test {

    @Test
    void branch0() {
        int result = BmiZone0026.compute(7);
        assertEquals(72, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0026.compute(49);
        assertTrue(result > 0);
    }

}
