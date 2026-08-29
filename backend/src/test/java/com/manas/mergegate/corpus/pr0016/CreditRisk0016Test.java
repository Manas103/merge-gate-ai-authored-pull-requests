package com.manas.mergegate.corpus.pr0016;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0016Test {

    @Test
    void branch0() {
        int result = CreditRisk0016.compute(72);
        assertEquals(779, result);
    }

    @Test
    void branch1() {
        int result = CreditRisk0016.compute(105);
        assertEquals(467, result);
    }

    @Test
    void branch2() {
        int result = CreditRisk0016.compute(293);
        assertEquals(2500, result);
    }

}
