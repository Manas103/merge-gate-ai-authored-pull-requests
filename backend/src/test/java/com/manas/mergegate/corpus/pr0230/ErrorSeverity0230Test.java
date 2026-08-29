package com.manas.mergegate.corpus.pr0230;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0230Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0230.compute(10);
        assertEquals(55, result);
    }

    @Test
    void branch2() {
        int result = ErrorSeverity0230.compute(420);
        assertEquals(4515, result);
    }

}
