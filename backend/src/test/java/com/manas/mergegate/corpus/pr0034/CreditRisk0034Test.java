package com.manas.mergegate.corpus.pr0034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0034Test {

    @Test
    void branch0() {
        int result = CreditRisk0034.compute(272);
        assertEquals(631, result);
    }

    @Test
    void branch1() {
        int result = CreditRisk0034.compute(403);
        assertEquals(4619, result);
    }

}
