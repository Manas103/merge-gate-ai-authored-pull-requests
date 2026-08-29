package com.manas.mergegate.corpus.pr0168;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0168Test {

    @Test
    void branch0() {
        int result = PasswordScore0168.compute(137);
        assertEquals(1446, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0168.compute(311);
        assertEquals(2960, result);
    }

}
