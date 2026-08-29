package com.manas.mergegate.corpus.pr0171;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0171Test {

    @Test
    void branch0() {
        int result = TaxBracket0171.compute(237);
        assertEquals(2385, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0171.compute(308);
        assertEquals(1849, result);
    }

}
