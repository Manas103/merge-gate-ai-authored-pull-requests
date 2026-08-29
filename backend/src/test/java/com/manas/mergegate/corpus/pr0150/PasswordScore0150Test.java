package com.manas.mergegate.corpus.pr0150;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0150Test {

    @Test
    void branch0() {
        int result = PasswordScore0150.compute(83);
        assertEquals(179, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0150.compute(703);
        assertEquals(7278, result);
    }

}
