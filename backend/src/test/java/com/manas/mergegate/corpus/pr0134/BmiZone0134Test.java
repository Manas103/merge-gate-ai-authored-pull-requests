package com.manas.mergegate.corpus.pr0134;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0134Test {

    @Test
    void branch0() {
        int result = BmiZone0134.compute(219);
        assertEquals(695, result);
    }

    @Test
    void branch1() {
        int result = BmiZone0134.compute(550);
        assertEquals(2927, result);
    }

}
