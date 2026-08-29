package com.manas.mergegate.corpus.pr0019;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0019Test {

    @Test
    void branch0() {
        int result = LateFee0019.compute(289);
        assertEquals(1744, result);
    }

}
