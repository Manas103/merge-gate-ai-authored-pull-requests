package com.manas.mergegate.corpus.pr0210;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0210Test {

    @Test
    void branch0() {
        int result = LatencyBudget0210.compute(75);
        assertEquals(836, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0210.compute(253);
        assertTrue(result > 0);
    }

}
