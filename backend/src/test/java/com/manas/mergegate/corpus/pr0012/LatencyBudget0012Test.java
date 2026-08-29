package com.manas.mergegate.corpus.pr0012;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0012Test {

    @Test
    void branch0() {
        int result = LatencyBudget0012.compute(3);
        assertEquals(65, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0012.compute(194);
        assertEquals(2348, result);
    }

}
