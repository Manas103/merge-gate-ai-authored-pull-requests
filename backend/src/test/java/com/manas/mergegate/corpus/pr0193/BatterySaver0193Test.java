package com.manas.mergegate.corpus.pr0193;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0193Test {

    @Test
    void branch0() {
        int result = BatterySaver0193.compute(22);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = BatterySaver0193.compute(295);
        assertTrue(result > 0);
    }

}
