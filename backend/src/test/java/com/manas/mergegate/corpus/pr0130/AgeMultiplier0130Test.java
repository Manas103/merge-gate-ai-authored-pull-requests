package com.manas.mergegate.corpus.pr0130;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AgeMultiplier0130Test {

    @Test
    void branch0() {
        int result = AgeMultiplier0130.compute(124);
        assertEquals(1198, result);
    }

}
