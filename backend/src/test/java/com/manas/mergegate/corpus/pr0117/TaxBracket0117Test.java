package com.manas.mergegate.corpus.pr0117;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0117Test {

    @Test
    void branch0() {
        int result = TaxBracket0117.compute(112);
        assertEquals(633, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0117.compute(160);
        assertEquals(1169, result);
    }

}
