package com.manas.mergegate.corpus.pr0088;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0088Test {

    @Test
    void branch0() {
        int result = CreditRisk0088.compute(0);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = CreditRisk0088.compute(7);
        assertEquals(179, result);
    }

}
