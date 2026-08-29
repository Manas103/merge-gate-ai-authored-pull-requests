package com.manas.mergegate.corpus.pr0061;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0061Test {

    @Test
    void branch0() {
        int result = RetryBackoff0061.compute(230);
        assertEquals(2312, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0061.compute(297);
        assertTrue(result > 0);
    }

}
