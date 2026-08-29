package com.manas.mergegate.corpus.pr0223;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0223Test {

    @Test
    void branch0() {
        int result = RetryBackoff0223.compute(151);
        assertEquals(1586, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0223.compute(278);
        assertTrue(result > 0);
    }

}
