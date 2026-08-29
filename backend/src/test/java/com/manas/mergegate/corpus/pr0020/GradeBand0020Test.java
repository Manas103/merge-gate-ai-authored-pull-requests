package com.manas.mergegate.corpus.pr0020;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0020Test {

    @Test
    void branch0() {
        int result = GradeBand0020.compute(82);
        assertTrue(result > 0);
    }

}
