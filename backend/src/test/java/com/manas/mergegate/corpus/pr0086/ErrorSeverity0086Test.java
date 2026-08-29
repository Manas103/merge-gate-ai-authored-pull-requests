package com.manas.mergegate.corpus.pr0086;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0086Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0086.compute(41);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0086.compute(226);
        assertTrue(result > 0);
    }

}
