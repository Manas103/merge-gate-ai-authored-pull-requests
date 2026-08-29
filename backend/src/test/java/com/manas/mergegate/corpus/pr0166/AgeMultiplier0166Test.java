package com.manas.mergegate.corpus.pr0166;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0166Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0166.compute(34);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0166.compute(504);
        assertTrue(result > 0);
    }

}
