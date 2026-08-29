package com.manas.mergegate.corpus.pr0207;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0207Test {

    @Test
    void branch0() {
        int result = TaxBracket0207.compute(80);
        assertEquals(617, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0207.compute(247);
        assertEquals(804, result);
    }

    @Test
    void branch2() {
        int result = TaxBracket0207.compute(358);
        assertEquals(3170, result);
    }

}
