package com.manas.mergegate.corpus.pr0103;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0103Test {

    @Test
    void branch0() {
        int result = BatterySaver0103.compute(171);
        assertEquals(1439, result);
    }

}
