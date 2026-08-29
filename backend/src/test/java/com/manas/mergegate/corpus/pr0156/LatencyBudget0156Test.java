package com.manas.mergegate.corpus.pr0156;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0156Test {

    @Test
    void branch0() {
        int result = LatencyBudget0156.compute(273);
        assertEquals(583, result);
    }

}
