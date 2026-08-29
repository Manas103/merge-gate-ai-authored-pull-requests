package com.manas.mergegate.corpus.pr0208;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0208Test {

    @Test
    void branch0() {
        int result = QueuePriority0208.compute(23);
        assertEquals(121, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0208.compute(136);
        assertEquals(1133, result);
    }

    @Test
    void branch2() {
        int result = QueuePriority0208.compute(180);
        assertEquals(1001, result);
    }

    @Test
    void branch3() {
        int result = QueuePriority0208.compute(228);
        assertTrue(result > 0);
    }

}
