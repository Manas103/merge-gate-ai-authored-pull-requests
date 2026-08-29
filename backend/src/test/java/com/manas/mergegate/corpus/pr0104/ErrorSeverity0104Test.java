package com.manas.mergegate.corpus.pr0104;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0104Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0104.compute(20);
        assertEquals(93, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0104.compute(166);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = ErrorSeverity0104.compute(398);
        assertTrue(result > 0);
    }

}
