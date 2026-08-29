package com.manas.mergegate.corpus.pr0092;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0092Test {

    @Test
    void branch0() {
        int result = GradeBand0092.compute(47);
        assertEquals(601, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0092.compute(266);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = GradeBand0092.compute(353);
        assertTrue(result > 0);
    }

}
