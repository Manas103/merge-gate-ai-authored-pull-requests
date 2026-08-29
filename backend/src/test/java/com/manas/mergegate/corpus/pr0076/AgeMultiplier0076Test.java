package com.manas.mergegate.corpus.pr0076;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0076Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0076.compute(78);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0076.compute(398);
        assertEquals(2699, result);
    }

}
