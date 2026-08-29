package com.manas.mergegate.corpus.pr0184;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0184Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0184.compute(44);
        assertEquals(455, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0184.compute(52);
        assertEquals(393, result);
    }

}
