package com.manas.mergegate.corpus.pr0176;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0176Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0176.compute(141);
        assertEquals(1005, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0176.compute(383);
        assertEquals(4101, result);
    }

}
