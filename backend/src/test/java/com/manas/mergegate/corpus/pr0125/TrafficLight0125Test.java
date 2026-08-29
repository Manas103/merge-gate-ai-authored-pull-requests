package com.manas.mergegate.corpus.pr0125;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0125Test {

    @Test
    void branch0() {
        int result = TrafficLight0125.compute(39);
        assertEquals(380, result);
    }

    @Test
    void branch1() {
        int result = TrafficLight0125.compute(257);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = TrafficLight0125.compute(355);
        assertTrue(result > 0);
    }

    @Test
    void branch3() {
        int result = TrafficLight0125.compute(533);
        assertEquals(6100, result);
    }

}
