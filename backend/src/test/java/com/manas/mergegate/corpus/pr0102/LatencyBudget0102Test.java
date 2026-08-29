package com.manas.mergegate.corpus.pr0102;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LatencyBudget0102Test {

    @Test
    void branch0() {
        int result = LatencyBudget0102.compute(21);
        assertEquals(122, result);
    }

    @Test
    void branch1() {
        int result = LatencyBudget0102.compute(363);
        assertEquals(3669, result);
    }

    @Test
    void branch2() {
        int result = LatencyBudget0102.compute(529);
        assertEquals(5558, result);
    }

}
