package com.manas.mergegate.corpus.pr0017;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0017Test {

    @Test
    void branch0() {
        int result = TrafficLight0017.compute(14);
        assertEquals(105, result);
    }

    @Test
    void branch2() {
        int result = TrafficLight0017.compute(291);
        assertTrue(result > 0);
    }

}
