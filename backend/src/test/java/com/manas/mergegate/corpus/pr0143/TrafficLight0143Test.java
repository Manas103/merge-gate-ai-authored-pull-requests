package com.manas.mergegate.corpus.pr0143;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0143Test {

    @Test
    void branch0() {
        int result = TrafficLight0143.compute(116);
        assertEquals(943, result);
    }

    @Test
    void branch1() {
        int result = TrafficLight0143.compute(281);
        assertEquals(983, result);
    }

}
