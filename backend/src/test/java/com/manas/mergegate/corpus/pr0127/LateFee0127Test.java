package com.manas.mergegate.corpus.pr0127;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0127Test {

    @Test
    void branch0() {
        int result = LateFee0127.compute(122);
        assertEquals(805, result);
    }

    @Test
    void branch1() {
        int result = LateFee0127.compute(514);
        assertEquals(1290, result);
    }

}
