package com.manas.mergegate.corpus.pr0109;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0109Test {

    @Test
    void branch0() {
        int result = LateFee0109.compute(80);
        assertEquals(553, result);
    }

}
