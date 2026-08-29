package com.manas.mergegate.corpus.pr0048;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0048Test {

    @Test
    void branch0() {
        int result = LatencyBudget0048.compute(28);
        assertEquals(155, result);
    }

    @Test
    void branch2() {
        int result = LatencyBudget0048.compute(543);
        assertEquals(3985, result);
    }

}
