package com.manas.mergegate.corpus.pr0107;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0107Test {

    @Test
    void branch0() {
        int result = TrafficLight0107.compute(17);
        assertEquals(143, result);
    }

    @Test
    void branch1() {
        int result = TrafficLight0107.compute(87);
        assertTrue(result > 0);
    }

}
