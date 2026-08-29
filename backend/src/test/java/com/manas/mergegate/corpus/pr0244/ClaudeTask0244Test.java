package com.manas.mergegate.corpus.pr0244;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClaudeTask0244Test {

    @Test
    void lowTrafficUsesSingleServer() {
        assertEquals(1, ClaudeTask0244.compute(0));
        assertEquals(1, ClaudeTask0244.compute(42));
        assertEquals(1, ClaudeTask0244.compute(99));
    }

    @Test
    void moderateTrafficUsesTwoServers() {
        assertEquals(2, ClaudeTask0244.compute(100));
        assertEquals(2, ClaudeTask0244.compute(310));
        assertEquals(2, ClaudeTask0244.compute(499));
    }

    @Test
    void heavyTrafficUsesSixServers() {
        assertEquals(6, ClaudeTask0244.compute(500));
        assertEquals(6, ClaudeTask0244.compute(1234));
        assertEquals(6, ClaudeTask0244.compute(1999));
    }

    @Test
    void peakTrafficUsesTwentyServers() {
        assertEquals(20, ClaudeTask0244.compute(2000));
        assertEquals(20, ClaudeTask0244.compute(50000));
        assertEquals(20, ClaudeTask0244.compute(Integer.MAX_VALUE));
    }

    @Test
    void negativeRequestRatesFallInLowestBand() {
        assertEquals(1, ClaudeTask0244.compute(-1));
        assertEquals(1, ClaudeTask0244.compute(Integer.MIN_VALUE));
    }
}
