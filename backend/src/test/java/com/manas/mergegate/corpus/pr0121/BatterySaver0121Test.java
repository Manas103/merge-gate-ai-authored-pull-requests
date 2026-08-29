package com.manas.mergegate.corpus.pr0121;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0121Test {

    @Test
    void branch0() {
        int result = BatterySaver0121.compute(76);
        assertEquals(355, result);
    }

    @Test
    void branch1() {
        int result = BatterySaver0121.compute(331);
        assertEquals(2863, result);
    }

}
