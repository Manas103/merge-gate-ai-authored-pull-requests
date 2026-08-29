package com.manas.mergegate.corpus.pr0196;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0196Test {

    @Test
    void branch0() {
        int result = CreditRisk0196.compute(222);
        assertEquals(2283, result);
    }

    @Test
    void branch1() {
        int result = CreditRisk0196.compute(394);
        assertTrue(result > 0);
    }

}
