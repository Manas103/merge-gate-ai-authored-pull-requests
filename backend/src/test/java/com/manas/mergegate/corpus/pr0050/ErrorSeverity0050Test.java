package com.manas.mergegate.corpus.pr0050;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0050Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0050.compute(36);
        assertEquals(326, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0050.compute(84);
        assertTrue(result > 0);
    }

}
