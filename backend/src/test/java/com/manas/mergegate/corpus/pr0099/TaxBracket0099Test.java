package com.manas.mergegate.corpus.pr0099;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxBracket0099Test {

    @Test
    void branch0() {
        int result = TaxBracket0099.compute(25);
        assertEquals(123, result);
    }

    @Test
    void branch1() {
        int result = TaxBracket0099.compute(85);
        assertEquals(1022, result);
    }

}
