package com.manas.mergegate.corpus.pr0211;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0211Test {

    @Test
    void branch0() {
        int result = BatterySaver0211.compute(155);
        assertEquals(1174, result);
    }

    @Test
    void branch1() {
        int result = BatterySaver0211.compute(346);
        assertEquals(1817, result);
    }

}
