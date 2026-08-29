package com.manas.mergegate.corpus.pr0064;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueuePriority0064Test {

    @Test
    void branch0() {
        int result = QueuePriority0064.compute(159);
        assertEquals(555, result);
    }

    @Test
    void branch1() {
        int result = QueuePriority0064.compute(563);
        assertEquals(1960, result);
    }

}
