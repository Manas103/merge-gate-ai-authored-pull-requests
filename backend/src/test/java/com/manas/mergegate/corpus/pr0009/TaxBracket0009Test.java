package com.manas.mergegate.corpus.pr0009;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0009Test {

    @Test
    void branch0() {
        int result = TaxBracket0009.compute(108);
        assertEquals(675, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0009.compute(333);
        assertEquals(3396, result);
    }

    @Test
    void branch2() {
        int result = TaxBracket0009.compute(350);
        assertTrue(result > 0);
    }

}
