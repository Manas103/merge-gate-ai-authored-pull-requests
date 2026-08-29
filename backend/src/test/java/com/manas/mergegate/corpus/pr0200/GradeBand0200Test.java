package com.manas.mergegate.corpus.pr0200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GradeBand0200Test {

    @Test
    void branch0() {
        int result = GradeBand0200.compute(83);
        assertEquals(975, result);
    }

    @Test
    void branch2() {
        int result = GradeBand0200.compute(404);
        assertEquals(2718, result);
    }

}
