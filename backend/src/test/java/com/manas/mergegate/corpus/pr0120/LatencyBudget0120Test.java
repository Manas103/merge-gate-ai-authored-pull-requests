package com.manas.mergegate.corpus.pr0120;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0120Test {

    @Test
    void branch0() {
        int result = LatencyBudget0120.compute(79);
        assertEquals(637, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0120.compute(135);
        assertTrue(result > 0);
    }

}
