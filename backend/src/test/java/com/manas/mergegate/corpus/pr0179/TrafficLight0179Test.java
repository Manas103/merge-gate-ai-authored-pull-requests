package com.manas.mergegate.corpus.pr0179;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0179Test {

    @Test
    void branch0() {
        int result = TrafficLight0179.compute(99);
        assertEquals(739, result);
    }

    @Test
    void branch1() {
        int result = TrafficLight0179.compute(159);
        assertEquals(850, result);
    }

    @Test
    void branch2() {
        int result = TrafficLight0179.compute(551);
        assertTrue(result > 0);
    }

}
