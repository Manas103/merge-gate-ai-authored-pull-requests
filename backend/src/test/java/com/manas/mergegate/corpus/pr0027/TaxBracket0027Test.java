package com.manas.mergegate.corpus.pr0027;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0027Test {

    @Test
    void branch0() {
        int result = TaxBracket0027.compute(12);
        assertEquals(86, result);
    }

}
