package com.manas.mergegate.corpus.pr0135;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0135Test {

    @Test
    void branch0() {
        int result = TaxBracket0135.compute(44);
        assertEquals(207, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0135.compute(299);
        assertTrue(result > 0);
    }

}
