package com.manas.mergegate.corpus.pr0160;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0160Test {

    @Test
    void branch0() {
        int result = CreditRisk0160.compute(258);
        assertEquals(1121, result);
    }

    @Test
    void branch1() {
        int result = CreditRisk0160.compute(453);
        assertTrue(result > 0);
    }

}
