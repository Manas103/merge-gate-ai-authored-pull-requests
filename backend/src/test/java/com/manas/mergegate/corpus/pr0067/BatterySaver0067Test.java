package com.manas.mergegate.corpus.pr0067;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0067Test {

    @Test
    void branch0() {
        int result = BatterySaver0067.compute(80);
        assertEquals(575, result);
    }

    @Test
    void branch1() {
        int result = BatterySaver0067.compute(348);
        assertTrue(result > 0);
    }

}
