package com.manas.mergegate.corpus.pr0063;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0063Test {

    @Test
    void branch0() {
        int result = TaxBracket0063.compute(3);
        assertEquals(59, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0063.compute(175);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = TaxBracket0063.compute(215);
        assertTrue(result > 0);
    }

}
