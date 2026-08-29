package com.manas.mergegate.corpus.pr0153;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0153Test {

    @Test
    void branch0() {
        int result = TaxBracket0153.compute(21);
        assertEquals(193, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0153.compute(324);
        assertEquals(1180, result);
    }

}
