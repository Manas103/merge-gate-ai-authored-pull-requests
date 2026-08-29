package com.manas.mergegate.corpus.pr0152;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0152Test {

    @Test
    void branch0() {
        int result = BmiZone0152.compute(23);
        assertEquals(179, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0152.compute(219);
        assertEquals(1616, result);
    }

}
