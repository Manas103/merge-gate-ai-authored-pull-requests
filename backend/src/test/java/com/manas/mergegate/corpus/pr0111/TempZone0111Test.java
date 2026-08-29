package com.manas.mergegate.corpus.pr0111;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0111Test {

    @Test
    void branch0() {
        int result = TempZone0111.compute(172);
        assertEquals(1612, result);
    }

    @Test
    void branch1() {
        int result = TempZone0111.compute(282);
        assertEquals(2057, result);
    }

}
