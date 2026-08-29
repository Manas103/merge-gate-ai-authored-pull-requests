package com.manas.mergegate.corpus.pr0058;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0058Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0058.compute(28);
        assertEquals(305, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0058.compute(95);
        assertEquals(409, result);
    }

}
