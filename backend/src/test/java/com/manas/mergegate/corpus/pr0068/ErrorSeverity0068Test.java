package com.manas.mergegate.corpus.pr0068;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorSeverity0068Test {

    @Test
    void branch0() {
        int result = ErrorSeverity0068.compute(77);
        assertEquals(771, result);
    }

    @Test
    void branch1() {
        int result = ErrorSeverity0068.compute(140);
        assertEquals(1156, result);
    }

    @Test
    void branch2() {
        int result = ErrorSeverity0068.compute(236);
        assertTrue(result > 0);
    }

    @Test
    void branch3() {
        int result = ErrorSeverity0068.compute(319);
        assertEquals(3726, result);
    }

}
