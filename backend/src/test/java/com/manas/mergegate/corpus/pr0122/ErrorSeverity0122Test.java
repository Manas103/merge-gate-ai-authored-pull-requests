package com.manas.mergegate.corpus.pr0122;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0122Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0122.compute(13);
        assertEquals(217, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0122.compute(83);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = ErrorSeverity0122.compute(237);
        assertEquals(806, result);
    }

}
