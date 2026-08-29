package com.manas.mergegate.corpus.pr0204;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0204Test {

    @Test
    void branch0() {
        int result = PasswordScore0204.compute(307);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = PasswordScore0204.compute(630);
        assertEquals(2002, result);
    }

}
