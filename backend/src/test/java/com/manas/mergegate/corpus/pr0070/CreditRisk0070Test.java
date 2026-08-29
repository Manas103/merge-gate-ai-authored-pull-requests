package com.manas.mergegate.corpus.pr0070;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0070Test {

    @Test
    void branch0() {
        int result = CreditRisk0070.compute(49);
        assertEquals(452, result);
    }

    @Test
    void branch1() {
        int result = CreditRisk0070.compute(261);
        assertEquals(2695, result);
    }

    @Test
    void branch2() {
        int result = CreditRisk0070.compute(350);
        assertTrue(result > 0);
    }

}
