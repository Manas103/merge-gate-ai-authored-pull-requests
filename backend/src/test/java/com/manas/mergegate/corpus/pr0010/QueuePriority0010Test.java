package com.manas.mergegate.corpus.pr0010;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0010Test {

    @Test
    void branch0() {
        int result = QueuePriority0010.compute(165);
        assertEquals(1233, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0010.compute(487);
        assertTrue(result > 0);
    }

}
