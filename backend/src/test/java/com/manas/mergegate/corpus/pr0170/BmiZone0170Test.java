package com.manas.mergegate.corpus.pr0170;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BmiZone0170Test {

    @Test
    void branch0() {
        int result = BmiZone0170.compute(10);
        assertTrue(result > 0);
    }

}
