package com.manas.mergegate.corpus.pr0145;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0145Test {

    @Test
    void branch0() {
        int result = LateFee0145.compute(2);
        assertEquals(86, result);
    }

    @Test
    void branch1() {
        int result = LateFee0145.compute(157);
        assertEquals(691, result);
    }

    @Test
    void branch2() {
        int result = LateFee0145.compute(388);
        assertEquals(2510, result);
    }

}
