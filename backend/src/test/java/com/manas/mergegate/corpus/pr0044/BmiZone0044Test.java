package com.manas.mergegate.corpus.pr0044;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0044Test {

    @Test
    void branch0() {
        int result = BmiZone0044.compute(80);
        assertTrue(result > 0);
    }

    @Test
    void branch1() {
        int result = BmiZone0044.compute(545);
        assertTrue(result > 0);
    }

}
