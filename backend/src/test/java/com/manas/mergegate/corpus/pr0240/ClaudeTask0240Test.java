package com.manas.mergegate.corpus.pr0240;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClaudeTask0240Test {

    @Test
    void freezingTemperaturesBlockCharging() {
        assertEquals(0, ClaudeTask0240.compute(-1));
        assertEquals(0, ClaudeTask0240.compute(-250));
        assertEquals(0, ClaudeTask0240.compute(Integer.MIN_VALUE));
    }

    @Test
    void coldTemperaturesChargeAtHalfRate() {
        assertEquals(50, ClaudeTask0240.compute(0));
        assertEquals(50, ClaudeTask0240.compute(75));
        assertEquals(50, ClaudeTask0240.compute(149));
    }

    @Test
    void nominalTemperaturesChargeAtFullRate() {
        assertEquals(100, ClaudeTask0240.compute(150));
        assertEquals(100, ClaudeTask0240.compute(300));
        assertEquals(100, ClaudeTask0240.compute(449));
    }

    @Test
    void hotTemperaturesBlockCharging() {
        assertEquals(0, ClaudeTask0240.compute(450));
        assertEquals(0, ClaudeTask0240.compute(600));
        assertEquals(0, ClaudeTask0240.compute(Integer.MAX_VALUE));
    }
}
