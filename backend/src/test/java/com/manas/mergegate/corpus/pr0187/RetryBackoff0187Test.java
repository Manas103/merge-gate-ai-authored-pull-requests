package com.manas.mergegate.corpus.pr0187;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0187Test {

    @Test
    void branch0() {
        int result = RetryBackoff0187.compute(64);
        assertEquals(595, result);
    }

}
