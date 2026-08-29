package com.manas.mergegate.corpus.pr0218;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0218Test {

    @Test
    void branch0() {
        int result = GradeBand0218.compute(22);
        assertEquals(197, result);
    }

    @Test
    void branch1() {
        int result = GradeBand0218.compute(318);
        assertEquals(3224, result);
    }

    @Test
    void branch2() {
        int result = GradeBand0218.compute(521);
        assertEquals(3276, result);
    }

}
