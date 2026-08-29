package com.manas.mergegate.corpus.pr0192;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0192Test {

    @Test
    void branch0() {
        int result = LatencyBudget0192.compute(133);
        assertEquals(968, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0192.compute(429);
        assertTrue(result > 0);
    }

}
