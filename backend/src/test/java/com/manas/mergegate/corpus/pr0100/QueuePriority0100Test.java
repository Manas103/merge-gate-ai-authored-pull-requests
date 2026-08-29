package com.manas.mergegate.corpus.pr0100;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0100Test {

    @Test
    void branch0() {
        int result = QueuePriority0100.compute(74);
        assertEquals(760, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0100.compute(208);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = QueuePriority0100.compute(675);
        assertEquals(2192, result);
    }

}
