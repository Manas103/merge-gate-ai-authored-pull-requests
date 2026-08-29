package com.manas.mergegate.corpus.pr0142;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0142Test {

    @Test
    void branch0() {
        int result = CreditRisk0142.compute(36);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = CreditRisk0142.compute(201);
        assertEquals(538, result);
    }

}
