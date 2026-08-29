package com.manas.mergegate.corpus.pr0025;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0025Test {

    @Test
    void branch0() {
        int result = RetryBackoff0025.compute(74);
        assertEquals(159, result);
    }

}
