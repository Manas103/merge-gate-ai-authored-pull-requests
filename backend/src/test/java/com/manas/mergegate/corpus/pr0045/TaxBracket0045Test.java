package com.manas.mergegate.corpus.pr0045;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0045Test {

    @Test
    void branch0() {
        int result = TaxBracket0045.compute(5);
        assertEquals(30, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0045.compute(154);
        assertEquals(604, result);
    }

}
