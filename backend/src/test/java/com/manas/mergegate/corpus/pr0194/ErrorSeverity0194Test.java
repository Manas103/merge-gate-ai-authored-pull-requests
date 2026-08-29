package com.manas.mergegate.corpus.pr0194;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0194Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0194.compute(22);
        assertTrue(result > 0);
    }

}
