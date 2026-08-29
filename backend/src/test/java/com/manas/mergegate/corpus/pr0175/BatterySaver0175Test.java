package com.manas.mergegate.corpus.pr0175;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0175Test {

    @Test
    void branch0() {
        int result = BatterySaver0175.compute(6);
        assertEquals(71, result);
    }

    @Test
    void branch1() {
        int result = BatterySaver0175.compute(21);
        assertEquals(402, result);
    }

}
