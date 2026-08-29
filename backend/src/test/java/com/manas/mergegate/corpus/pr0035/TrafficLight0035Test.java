package com.manas.mergegate.corpus.pr0035;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0035Test {

    @Test
    void branch0() {
        int result = TrafficLight0035.compute(12);
        assertEquals(106, result);
    }

    @Test
    void branch1() {
        int result = TrafficLight0035.compute(352);
        assertTrue(result > 0);
    }

}
