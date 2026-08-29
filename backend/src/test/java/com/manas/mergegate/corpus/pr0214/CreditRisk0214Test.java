package com.manas.mergegate.corpus.pr0214;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0214Test {

    @Test
    void branch0() {
        int result = CreditRisk0214.compute(190);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = CreditRisk0214.compute(335);
        assertTrue(result > 0);
    }

}
