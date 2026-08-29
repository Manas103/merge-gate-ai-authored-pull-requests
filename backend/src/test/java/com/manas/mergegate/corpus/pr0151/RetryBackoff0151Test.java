package com.manas.mergegate.corpus.pr0151;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0151Test {

    @Test
    void branch0() {
        int result = RetryBackoff0151.compute(139);
        assertEquals(337, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0151.compute(267);
        assertEquals(1371, result);
    }

}
