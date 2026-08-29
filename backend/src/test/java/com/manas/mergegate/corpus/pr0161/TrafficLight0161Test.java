package com.manas.mergegate.corpus.pr0161;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0161Test {

    @Test
    void branch0() {
        int result = TrafficLight0161.compute(165);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = TrafficLight0161.compute(207);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = TrafficLight0161.compute(239);
        assertTrue(result > 0);
    }

}
