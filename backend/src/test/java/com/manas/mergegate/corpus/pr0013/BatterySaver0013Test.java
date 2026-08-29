package com.manas.mergegate.corpus.pr0013;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0013Test {

    @Test
    void branch0() {
        int result = BatterySaver0013.compute(175);
        assertEquals(1833, result);
    }

    @Test
    void branch2() {
        int result = BatterySaver0013.compute(315);
        assertEquals(3340, result);
    }

}
