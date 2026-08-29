package com.manas.mergegate.corpus.pr0205;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0205Test {

    @Test
    void branch0() {
        int result = RetryBackoff0205.compute(86);
        assertEquals(909, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0205.compute(308);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = RetryBackoff0205.compute(663);
        assertEquals(7502, result);
    }

}
