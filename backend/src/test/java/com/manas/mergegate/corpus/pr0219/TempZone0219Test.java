package com.manas.mergegate.corpus.pr0219;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0219Test {

    @Test
    void branch0() {
        int result = TempZone0219.compute(2);
        assertEquals(82, result);
    }

    @Test
    void branch1() {
        int result = TempZone0219.compute(177);
        assertEquals(2153, result);
    }

}
