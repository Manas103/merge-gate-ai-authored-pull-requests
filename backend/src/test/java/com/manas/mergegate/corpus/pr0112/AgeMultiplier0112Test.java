package com.manas.mergegate.corpus.pr0112;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0112Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0112.compute(43);
        assertEquals(400, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0112.compute(347);
        assertTrue(result > 0);
    }

}
