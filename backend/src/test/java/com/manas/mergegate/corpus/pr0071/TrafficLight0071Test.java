package com.manas.mergegate.corpus.pr0071;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0071Test {

    @Test
    void branch0() {
        int result = TrafficLight0071.compute(225);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = TrafficLight0071.compute(380);
        assertEquals(3228, result);
    }

}
