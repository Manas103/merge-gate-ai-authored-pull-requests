package com.manas.mergegate.corpus.pr0080;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0080Test {

    @Test
    void branch0() {
        int result = BmiZone0080.compute(164);
        assertEquals(837, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0080.compute(233);
        assertEquals(1385, result);
    }

}
