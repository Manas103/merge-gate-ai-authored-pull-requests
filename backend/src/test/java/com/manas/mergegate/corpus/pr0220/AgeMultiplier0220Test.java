package com.manas.mergegate.corpus.pr0220;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0220Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0220.compute(112);
        assertEquals(917, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0220.compute(299);
        assertEquals(2353, result);
    }

}
