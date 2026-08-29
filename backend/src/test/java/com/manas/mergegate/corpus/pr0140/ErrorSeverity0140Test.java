package com.manas.mergegate.corpus.pr0140;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0140Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0140.compute(91);
        assertEquals(993, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0140.compute(315);
        assertEquals(2077, result);
    }

}
