package com.manas.mergegate.corpus.pr0089;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0089Test {

    @Test
    void branch0() {
        int result = TrafficLight0089.compute(98);
        assertEquals(761, result);
    }

    @Test
    void branch1() {
        int result = TrafficLight0089.compute(546);
        assertTrue(result > 0);
    }

}
