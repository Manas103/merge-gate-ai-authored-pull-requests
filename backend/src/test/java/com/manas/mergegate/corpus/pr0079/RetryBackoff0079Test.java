package com.manas.mergegate.corpus.pr0079;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0079Test {

    @Test
    void branch0() {
        int result = RetryBackoff0079.compute(312);
        assertEquals(1024, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0079.compute(338);
        assertTrue(result > 0);
    }

}
