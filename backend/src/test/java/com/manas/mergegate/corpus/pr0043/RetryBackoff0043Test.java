package com.manas.mergegate.corpus.pr0043;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0043Test {

    @Test
    void branch0() {
        int result = RetryBackoff0043.compute(1);
        assertEquals(90, result);
    }

    @Test
    void branch2() {
        int result = RetryBackoff0043.compute(413);
        assertEquals(1865, result);
    }

}
