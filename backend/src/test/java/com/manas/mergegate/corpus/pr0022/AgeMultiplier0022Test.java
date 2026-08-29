package com.manas.mergegate.corpus.pr0022;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0022Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0022.compute(0);
        assertEquals(41, result);
    }

    @Test
    void branch1() {
        int result = AgeMultiplier0022.compute(109);
        assertEquals(919, result);
    }

}
