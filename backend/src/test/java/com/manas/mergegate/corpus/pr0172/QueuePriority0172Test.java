package com.manas.mergegate.corpus.pr0172;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0172Test {

    @Test
    void branch0() {
        int result = QueuePriority0172.compute(3);
        assertEquals(45, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0172.compute(106);
        assertEquals(956, result);
    }

}
