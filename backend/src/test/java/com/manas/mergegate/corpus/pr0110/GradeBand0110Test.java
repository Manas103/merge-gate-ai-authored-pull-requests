package com.manas.mergegate.corpus.pr0110;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0110Test {

    @Test
    void branch0() {
        int result = GradeBand0110.compute(250);
        assertEquals(1559, result);
    }

}
