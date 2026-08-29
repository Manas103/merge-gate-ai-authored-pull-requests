package com.manas.mergegate.corpus.pr0148;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0148Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0148.compute(19);
        assertEquals(219, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0148.compute(151);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = AgeMultiplier0148.compute(250);
        assertEquals(2895, result);
    }

}
