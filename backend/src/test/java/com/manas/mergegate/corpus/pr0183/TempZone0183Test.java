package com.manas.mergegate.corpus.pr0183;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0183Test {

    @Test
    void branch0() {
        int result = TempZone0183.compute(198);
        assertEquals(1004, result);
    }

    @Test
    void branch1() {
        int result = TempZone0183.compute(271);
        assertEquals(1744, result);
    }

}
