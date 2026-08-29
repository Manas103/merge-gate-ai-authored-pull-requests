package com.manas.mergegate.corpus.pr0212;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0212Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0212.compute(7);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0212.compute(493);
        assertEquals(5701, result);
    }

}
