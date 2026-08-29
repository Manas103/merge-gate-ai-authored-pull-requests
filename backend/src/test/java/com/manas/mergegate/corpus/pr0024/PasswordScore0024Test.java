package com.manas.mergegate.corpus.pr0024;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0024Test {

    @Test
    void branch0() {
        int result = PasswordScore0024.compute(127);
        assertEquals(1428, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0024.compute(349);
        assertEquals(3297, result);
    }

}
