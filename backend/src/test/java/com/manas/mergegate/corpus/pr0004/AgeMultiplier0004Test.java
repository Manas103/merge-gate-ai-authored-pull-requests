package com.manas.mergegate.corpus.pr0004;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0004Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0004.compute(158);
        assertEquals(1604, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0004.compute(486);
        assertEquals(4554, result);
    }

}
