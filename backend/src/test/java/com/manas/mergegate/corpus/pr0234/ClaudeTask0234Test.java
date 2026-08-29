package com.manas.mergegate.corpus.pr0234;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClaudeTask0234Test {

    @Test
    void negativeFloorsAwayScoresZero() {
        assertEquals(0, ClaudeTask0234.compute(-1));
        assertEquals(0, ClaudeTask0234.compute(-25));
    }

    @Test
    void sameFloorScoresHighest() {
        assertEquals(100, ClaudeTask0234.compute(0));
    }

    @Test
    void nearFloorsUseTheSteepBand() {
        assertEquals(90, ClaudeTask0234.compute(1));
        assertEquals(80, ClaudeTask0234.compute(2));
        assertEquals(70, ClaudeTask0234.compute(3));
    }

    @Test
    void midRangeFloorsUseTheShallowBand() {
        assertEquals(44, ClaudeTask0234.compute(4));
        assertEquals(28, ClaudeTask0234.compute(8));
        assertEquals(20, ClaudeTask0234.compute(10));
    }

    @Test
    void farFloorsFallBackToTheFloorScore() {
        assertEquals(5, ClaudeTask0234.compute(11));
        assertEquals(5, ClaudeTask0234.compute(50));
        assertEquals(5, ClaudeTask0234.compute(Integer.MAX_VALUE));
    }

    @Test
    void scoreNeverIncreasesWithDistanceWithinValidRange() {
        for (int i = 0; i < 20; i++) {
            assertTrue(ClaudeTask0234.compute(i) >= ClaudeTask0234.compute(i + 1));
        }
    }
}
