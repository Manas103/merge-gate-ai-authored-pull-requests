package com.manas.mergegate.corpus.pr0085;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatterySaver0085Test {

    @Test
    void branch0() {
        int result = BatterySaver0085.compute(60);
        assertTrue(result > 0);
    }

}
