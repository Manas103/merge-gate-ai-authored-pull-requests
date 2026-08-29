package com.manas.mergegate.corpus.pr0222;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0222Test {

    @Test
    void branch0() {
        int result = PasswordScore0222.compute(119);
        assertEquals(550, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0222.compute(311);
        assertTrue(result > 0);
    }

}
