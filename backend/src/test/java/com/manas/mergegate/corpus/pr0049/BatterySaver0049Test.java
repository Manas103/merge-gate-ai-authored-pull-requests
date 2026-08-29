package com.manas.mergegate.corpus.pr0049;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0049Test {

    @Test
    void branch0() {
        int result = BatterySaver0049.compute(16);
        assertEquals(160, result);
    }

    @Test
    void branch1() {
        int result = BatterySaver0049.compute(550);
        assertEquals(6167, result);
    }

}
