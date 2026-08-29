package com.manas.mergegate.corpus.pr0215;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrafficLight0215Test {

    @Test
    void branch0() {
        int result = TrafficLight0215.compute(18);
        assertEquals(206, result);
    }

}
