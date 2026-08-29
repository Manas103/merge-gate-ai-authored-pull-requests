package com.manas.mergegate.corpus.pr0182;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0182Test {

    @Test
    void branch0() {
        int result = GradeBand0182.compute(105);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = GradeBand0182.compute(183);
        assertTrue(result > 0);
    }

}
