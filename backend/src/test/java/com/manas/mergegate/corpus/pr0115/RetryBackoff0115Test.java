package com.manas.mergegate.corpus.pr0115;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetryBackoff0115Test {

    @Test
    void branch0() {
        int result = RetryBackoff0115.compute(148);
        assertEquals(620, result);
    }

    @Test
    void branch1() {
        int result = RetryBackoff0115.compute(359);
        assertEquals(774, result);
    }

}
