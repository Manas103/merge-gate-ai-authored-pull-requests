package com.manas.mergegate.corpus.pr0138;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0138Test {

    @Test
    void branch0() {
        int result = LatencyBudget0138.compute(156);
        assertEquals(992, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0138.compute(485);
        assertEquals(4594, result);
    }

}
