package com.manas.mergegate.corpus.pr0106;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0106Test {

    @Test
    void branch0() {
        int result = CreditRisk0106.compute(117);
        assertEquals(891, result);
    }

    @Test
    void branch1() {
        int result = CreditRisk0106.compute(348);
        assertEquals(3682, result);
    }

}
