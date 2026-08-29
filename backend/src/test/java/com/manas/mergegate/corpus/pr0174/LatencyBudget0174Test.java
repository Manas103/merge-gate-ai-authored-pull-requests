package com.manas.mergegate.corpus.pr0174;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0174Test {

    @Test
    void branch0() {
        int result = LatencyBudget0174.compute(147);
        assertEquals(313, result);
    }

}
