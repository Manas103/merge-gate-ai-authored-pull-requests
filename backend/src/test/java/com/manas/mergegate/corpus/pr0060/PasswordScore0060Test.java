package com.manas.mergegate.corpus.pr0060;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordScore0060Test {

    @Test
    void branch0() {
        int result = PasswordScore0060.compute(28);
        assertTrue(result > 0);
    }

}
