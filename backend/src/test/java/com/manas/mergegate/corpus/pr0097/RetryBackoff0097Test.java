package com.manas.mergegate.corpus.pr0097;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0097Test {

    @Test
    void branch0() {
        int result = RetryBackoff0097.compute(110);
        assertEquals(508, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0097.compute(388);
        assertEquals(923, result);
    }

}
