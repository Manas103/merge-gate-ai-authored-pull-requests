package com.manas.mergegate.corpus.pr0014;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0014Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0014.compute(56);
        assertEquals(546, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0014.compute(275);
        assertEquals(1568, result);
    }

}
