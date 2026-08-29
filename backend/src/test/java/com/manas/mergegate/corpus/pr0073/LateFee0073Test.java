package com.manas.mergegate.corpus.pr0073;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0073Test {

    @Test
    void branch0() {
        int result = LateFee0073.compute(157);
        assertEquals(554, result);
    }

    @Test
    void branch1() {
        int result = LateFee0073.compute(242);
        assertEquals(671, result);
    }

}
