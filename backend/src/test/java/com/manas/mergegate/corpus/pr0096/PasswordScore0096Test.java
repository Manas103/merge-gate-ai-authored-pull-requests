package com.manas.mergegate.corpus.pr0096;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0096Test {

    @Test
    void branch0() {
        int result = PasswordScore0096.compute(229);
        assertEquals(471, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0096.compute(440);
        assertTrue(result > 0);
    }

}
