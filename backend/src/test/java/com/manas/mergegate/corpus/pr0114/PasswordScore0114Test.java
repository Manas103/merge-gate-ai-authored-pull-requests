package com.manas.mergegate.corpus.pr0114;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0114Test {

    @Test
    void branch0() {
        int result = PasswordScore0114.compute(46);
        assertEquals(340, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0114.compute(340);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = PasswordScore0114.compute(617);
        assertEquals(3978, result);
    }

}
