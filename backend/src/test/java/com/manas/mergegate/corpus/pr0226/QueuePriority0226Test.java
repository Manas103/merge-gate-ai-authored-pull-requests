package com.manas.mergegate.corpus.pr0226;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0226Test {

    @Test
    void branch0() {
        int result = QueuePriority0226.compute(55);
        assertEquals(171, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0226.compute(71);
        assertEquals(441, result);
    }

    @Test
    void branch2() {
        int result = QueuePriority0226.compute(170);
        assertEquals(1358, result);
    }

}
