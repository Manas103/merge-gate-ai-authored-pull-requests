package com.manas.mergegate.corpus.pr0178;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0178Test {

    @Test
    void branch0() {
        int result = CreditRisk0178.compute(64);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = CreditRisk0178.compute(326);
        assertTrue(result > 0);
    }

}
