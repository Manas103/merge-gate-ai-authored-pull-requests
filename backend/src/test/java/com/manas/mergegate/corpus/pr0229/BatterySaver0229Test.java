package com.manas.mergegate.corpus.pr0229;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0229Test {

    @Test
    void branch0() {
        int result = BatterySaver0229.compute(20);
        assertEquals(301, result);
    }

    @Test
    void branch1() {
        int result = BatterySaver0229.compute(221);
        assertEquals(1083, result);
    }

}
