package com.manas.mergegate.corpus.pr0098;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0098Test {

    @Test
    void branch0() {
        int result = BmiZone0098.compute(25);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = BmiZone0098.compute(161);
        assertEquals(559, result);
    }

    @Test
    void branch2() {
        int result = BmiZone0098.compute(425);
        assertEquals(3561, result);
    }

}
