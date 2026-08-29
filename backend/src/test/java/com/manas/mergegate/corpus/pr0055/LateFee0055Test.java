package com.manas.mergegate.corpus.pr0055;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0055Test {

    @Test
    void branch0() {
        int result = LateFee0055.compute(41);
        assertEquals(105, result);
    }

    @Test
    void branch1() {
        int result = LateFee0055.compute(230);
        assertEquals(2832, result);
    }

}
