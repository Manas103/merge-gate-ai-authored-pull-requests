package com.manas.mergegate.corpus.pr0006;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0006Test {

    @Test
    void branch0() {
        int result = PasswordScore0006.compute(208);
        assertEquals(913, result);
    }

    @Test
    void branch1() {
        int result = PasswordScore0006.compute(280);
        assertEquals(2638, result);
    }

}
