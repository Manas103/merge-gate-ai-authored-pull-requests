package com.manas.mergegate.corpus.pr0074;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0074Test {

    @Test
    void branch0() {
        int result = GradeBand0074.compute(81);
        assertEquals(273, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0074.compute(309);
        assertEquals(2098, result);
    }

}
