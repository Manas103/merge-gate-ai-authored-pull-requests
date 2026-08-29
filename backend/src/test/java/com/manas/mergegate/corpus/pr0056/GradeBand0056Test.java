package com.manas.mergegate.corpus.pr0056;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0056Test {

    @Test
    void branch0() {
        int result = GradeBand0056.compute(143);
        assertEquals(733, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0056.compute(336);
        assertTrue(result > 0);
    }

}
