package com.manas.mergegate.corpus.pr0046;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0046Test {

    @Test
    void branch0() {
        int result = QueuePriority0046.compute(20);
        assertEquals(230, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0046.compute(79);
        assertTrue(result > 0);
    }

    @Test
    void branch2() {
        int result = QueuePriority0046.compute(204);
        assertEquals(585, result);
    }

}
