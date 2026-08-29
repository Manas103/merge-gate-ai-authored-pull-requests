package com.manas.mergegate.corpus.pr0030;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0030Test {

    @Test
    void branch0() {
        int result = LatencyBudget0030.compute(103);
        assertEquals(732, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0030.compute(220);
        assertEquals(1419, result);
    }

}
