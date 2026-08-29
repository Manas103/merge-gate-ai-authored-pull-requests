package com.manas.mergegate.corpus.pr0038;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0038Test {

    @Test
    void branch0() {
        int result = GradeBand0038.compute(0);
        assertEquals(38, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0038.compute(153);
        assertEquals(625, result);
    }

}
