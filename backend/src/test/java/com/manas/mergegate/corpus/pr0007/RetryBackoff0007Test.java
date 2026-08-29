package com.manas.mergegate.corpus.pr0007;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0007Test {

    @Test
    void branch0() {
        int result = RetryBackoff0007.compute(135);
        assertEquals(603, result);
    }

}
