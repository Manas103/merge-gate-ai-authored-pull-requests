package com.manas.mergegate.corpus.pr0128;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0128Test {

    @Test
    void branch0() {
        int result = GradeBand0128.compute(12);
        assertEquals(117, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0128.compute(268);
        assertTrue(result > 0);
    }

}
