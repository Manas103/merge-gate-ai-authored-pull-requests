package com.manas.mergegate.corpus.pr0042;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0042Test {

    @Test
    void branch0() {
        int result = PasswordScore0042.compute(81);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = PasswordScore0042.compute(138);
        assertEquals(1740, result);
    }

}
