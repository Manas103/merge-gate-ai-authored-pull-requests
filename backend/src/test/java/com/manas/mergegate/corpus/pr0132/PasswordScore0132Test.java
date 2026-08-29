package com.manas.mergegate.corpus.pr0132;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0132Test {

    @Test
    void branch0() {
        int result = PasswordScore0132.compute(47);
        assertEquals(396, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0132.compute(154);
        assertTrue(result > 0);
    }

}
