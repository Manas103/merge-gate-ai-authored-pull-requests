package com.manas.mergegate.corpus.pr0053;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0053Test {

    @Test
    void branch0() {
        int result = TrafficLight0053.compute(154);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = TrafficLight0053.compute(409);
        assertEquals(1823, result);
    }

}
