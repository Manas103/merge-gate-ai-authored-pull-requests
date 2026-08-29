package com.manas.mergegate.corpus.pr0040;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0040Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0040.compute(40);
        assertEquals(410, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0040.compute(228);
        assertEquals(668, result);
    }

}
