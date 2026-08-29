package com.manas.mergegate.corpus.pr0066;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0066Test {

    @Test
    void branch0() {
        int result = LatencyBudget0066.compute(323);
        assertEquals(1323, result);
    }

}
