package com.manas.mergegate.corpus.pr0118;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0118Test {

    @Test
    void branch0() {
        int result = QueuePriority0118.compute(42);
        assertEquals(111, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0118.compute(315);
        assertEquals(850, result);
    }

}
