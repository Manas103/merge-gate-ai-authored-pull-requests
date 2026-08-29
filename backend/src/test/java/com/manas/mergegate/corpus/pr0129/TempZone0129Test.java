package com.manas.mergegate.corpus.pr0129;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0129Test {

    @Test
    void branch0() {
        int result = TempZone0129.compute(148);
        assertEquals(1098, result);
    }

    @Test
    void branch1() {
        int result = TempZone0129.compute(338);
        assertEquals(1487, result);
    }

}
