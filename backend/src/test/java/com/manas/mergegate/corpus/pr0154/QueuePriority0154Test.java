package com.manas.mergegate.corpus.pr0154;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0154Test {

    @Test
    void branch0() {
        int result = QueuePriority0154.compute(62);
        assertEquals(315, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0154.compute(174);
        assertEquals(1056, result);
    }

}
