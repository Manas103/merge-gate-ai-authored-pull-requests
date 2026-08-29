package com.manas.mergegate.corpus.pr0008;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0008Test {

    @Test
    void branch0() {
        int result = BmiZone0008.compute(158);
        assertEquals(372, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0008.compute(294);
        assertEquals(2733, result);
    }

}
