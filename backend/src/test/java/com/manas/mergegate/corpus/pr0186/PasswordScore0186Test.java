package com.manas.mergegate.corpus.pr0186;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0186Test {

    @Test
    void branch0() {
        int result = PasswordScore0186.compute(2);
        assertEquals(108, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0186.compute(226);
        assertTrue(result > 0);
    }

}
