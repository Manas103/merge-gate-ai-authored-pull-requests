package com.manas.mergegate.corpus.pr0201;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TempZone0201Test {

    @Test
    void branch0() {
        int result = TempZone0201.compute(34);
        assertEquals(348, result);
    }

    @Test
    void branch1() {
        int result = TempZone0201.compute(301);
        assertEquals(2140, result);
    }

}
