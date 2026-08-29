package com.manas.mergegate.corpus.pr0052;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditRisk0052Test {

    @Test
    void branch0() {
        int result = CreditRisk0052.compute(154);
        assertEquals(1274, result);
    }

    @Test
    void branch1() {
        int result = CreditRisk0052.compute(230);
        assertEquals(1003, result);
    }

}
