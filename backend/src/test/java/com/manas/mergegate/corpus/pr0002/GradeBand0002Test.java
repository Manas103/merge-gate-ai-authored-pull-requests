package com.manas.mergegate.corpus.pr0002;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0002Test {

    @Test
    void branch0() {
        int result = GradeBand0002.compute(97);
        assertEquals(373, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0002.compute(281);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = GradeBand0002.compute(325);
        assertEquals(2152, result);
    }

}
