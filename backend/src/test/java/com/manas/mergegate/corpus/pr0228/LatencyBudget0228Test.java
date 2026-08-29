package com.manas.mergegate.corpus.pr0228;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0228Test {

    @Test
    void branch0() {
        int result = LatencyBudget0228.compute(254);
        assertEquals(2601, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0228.compute(436);
        assertTrue(result > 0);
    }

}
