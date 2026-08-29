package com.manas.mergegate.corpus.pr0133;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0133Test {

    @Test
    void branch0() {
        int result = RetryBackoff0133.compute(110);
        assertEquals(1266, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0133.compute(410);
        assertEquals(2211, result);
    }

}
