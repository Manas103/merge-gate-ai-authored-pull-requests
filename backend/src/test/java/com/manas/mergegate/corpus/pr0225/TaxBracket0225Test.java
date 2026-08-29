package com.manas.mergegate.corpus.pr0225;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0225Test {

    @Test
    void branch0() {
        int result = TaxBracket0225.compute(155);
        assertEquals(864, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0225.compute(426);
        assertEquals(3233, result);
    }

}
