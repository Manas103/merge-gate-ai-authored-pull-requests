package com.manas.mergegate.corpus.pr0116;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0116Test {

    @Test
    void branch0() {
        int result = BmiZone0116.compute(5);
        assertEquals(118, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0116.compute(195);
        assertEquals(1906, result);
    }

}
