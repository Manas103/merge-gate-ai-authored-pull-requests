package com.manas.mergegate.corpus.pr0146;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0146Test {

    @Test
    void branch0() {
        int result = GradeBand0146.compute(16);
        assertEquals(152, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0146.compute(42);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = GradeBand0146.compute(406);
        assertEquals(4211, result);
    }

}
