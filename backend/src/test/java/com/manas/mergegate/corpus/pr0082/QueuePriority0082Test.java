package com.manas.mergegate.corpus.pr0082;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0082Test {

    @Test
    void branch0() {
        int result = QueuePriority0082.compute(3);
        assertEquals(68, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0082.compute(388);
        assertEquals(2474, result);
    }

}
