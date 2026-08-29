package com.manas.mergegate.corpus.pr0158;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0158Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0158.compute(20);
        assertEquals(138, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0158.compute(209);
        assertEquals(2013, result);
    }

}
