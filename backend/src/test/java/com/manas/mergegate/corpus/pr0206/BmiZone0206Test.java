package com.manas.mergegate.corpus.pr0206;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0206Test {

    @Test
    void branch0() {
        int result = BmiZone0206.compute(96);
        assertEquals(539, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0206.compute(126);
        assertEquals(1433, result);
    }

}
