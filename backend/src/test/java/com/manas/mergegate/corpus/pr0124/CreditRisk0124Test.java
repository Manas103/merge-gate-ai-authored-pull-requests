package com.manas.mergegate.corpus.pr0124;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0124Test {

    @Test
    void branch0() {
        int result = CreditRisk0124.compute(179);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = CreditRisk0124.compute(222);
        assertEquals(2509, result);
    }

}
