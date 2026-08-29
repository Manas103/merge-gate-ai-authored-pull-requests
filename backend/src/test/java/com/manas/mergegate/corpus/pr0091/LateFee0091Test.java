package com.manas.mergegate.corpus.pr0091;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0091Test {

    @Test
    void branch0() {
        int result = LateFee0091.compute(8);
        assertEquals(47, result);
    }

    @Test
    void branch1() {
        int result = LateFee0091.compute(108);
        assertEquals(499, result);
    }

}
