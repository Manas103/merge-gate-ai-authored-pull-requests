package com.manas.mergegate.corpus.pr0164;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0164Test {

    @Test
    void branch0() {
        int result = GradeBand0164.compute(10);
        assertEquals(183, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0164.compute(122);
        assertTrue(result > 0);
    }

}
