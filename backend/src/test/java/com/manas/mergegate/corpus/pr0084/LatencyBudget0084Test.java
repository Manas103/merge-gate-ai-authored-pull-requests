package com.manas.mergegate.corpus.pr0084;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0084Test {

    @Test
    void branch0() {
        int result = LatencyBudget0084.compute(85);
        assertEquals(207, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0084.compute(274);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = LatencyBudget0084.compute(388);
        assertEquals(1002, result);
    }

}
