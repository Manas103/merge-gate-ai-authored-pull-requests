package com.manas.mergegate.corpus.pr0189;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0189Test {

    @Test
    void branch0() {
        int result = TaxBracket0189.compute(3);
        assertEquals(46, result);
    }

    @Test
    void branch2() {
        int result = TaxBracket0189.compute(394);
        assertEquals(2264, result);
    }

}
