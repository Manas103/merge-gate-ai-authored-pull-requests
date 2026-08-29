package com.manas.mergegate.corpus.pr0163;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LateFee0163Test {

    @Test
    void branch0() {
        int result = LateFee0163.compute(41);
        assertEquals(509, result);
    }

    @Test
    void branch1() {
        int result = LateFee0163.compute(421);
        assertEquals(3581, result);
    }

}
