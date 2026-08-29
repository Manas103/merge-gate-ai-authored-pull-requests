package com.manas.mergegate.corpus.pr0032;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0032Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0032.compute(10);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0032.compute(312);
        assertEquals(2612, result);
    }

}
