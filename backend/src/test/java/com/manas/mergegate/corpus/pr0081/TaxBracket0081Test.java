package com.manas.mergegate.corpus.pr0081;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0081Test {

    @Test
    void branch0() {
        int result = TaxBracket0081.compute(26);
        assertEquals(162, result);
    }

}
