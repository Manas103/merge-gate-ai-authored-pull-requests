package com.manas.mergegate.corpus.pr0224;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0224Test {

    @Test
    void branch0() {
        int result = BmiZone0224.compute(36);
        assertEquals(393, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0224.compute(137);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = BmiZone0224.compute(204);
        assertTrue(result > 0);
    }

}
