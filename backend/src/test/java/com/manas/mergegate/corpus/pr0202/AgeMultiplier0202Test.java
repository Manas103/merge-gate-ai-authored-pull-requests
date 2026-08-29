package com.manas.mergegate.corpus.pr0202;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0202Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0202.compute(0);
        assertEquals(88, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0202.compute(44);
        assertTrue(result > 0);
    }

}
