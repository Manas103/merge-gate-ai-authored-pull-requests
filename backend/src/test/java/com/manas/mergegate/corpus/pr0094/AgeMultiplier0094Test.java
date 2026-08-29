package com.manas.mergegate.corpus.pr0094;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0094Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0094.compute(52);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0094.compute(242);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = AgeMultiplier0094.compute(301);
        assertTrue(result > 0);
    }

}
