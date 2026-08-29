package com.manas.mergegate.corpus.pr0169;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0169Test {

    @Test
    void branch0() {
        int result = RetryBackoff0169.compute(52);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0169.compute(260);
        assertTrue(result > 0);
    }

}
