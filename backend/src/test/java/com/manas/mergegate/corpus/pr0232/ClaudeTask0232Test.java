package com.manas.mergegate.corpus.pr0232;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClaudeTask0232Test {

    @Test
    void chargesFlatMinimumForSmallDeclaredValues() {
        assertEquals(250, ClaudeTask0232.compute(0));
        assertEquals(250, ClaudeTask0232.compute(1));
        assertEquals(250, ClaudeTask0232.compute(4));
    }

    @Test
    void treatsNonPositiveDeclaredValuesAsMinimum() {
        assertEquals(250, ClaudeTask0232.compute(-1));
        assertEquals(250, ClaudeTask0232.compute(-100));
    }

    @Test
    void chargesFortyCentsPerHundredInMiddleBand() {
        assertEquals(250, ClaudeTask0232.compute(5));
        assertEquals(290, ClaudeTask0232.compute(6));
        assertEquals(650, ClaudeTask0232.compute(15));
        assertEquals(2010, ClaudeTask0232.compute(49));
    }

    @Test
    void chargesTwentyFiveCentsPerHundredInTopBand() {
        assertEquals(2050, ClaudeTask0232.compute(50));
        assertEquals(2075, ClaudeTask0232.compute(51));
        assertEquals(3300, ClaudeTask0232.compute(100));
    }

    @Test
    void isContinuousAcrossBandBoundaries() {
        assertEquals(ClaudeTask0232.compute(4), ClaudeTask0232.compute(5));
        assertEquals(ClaudeTask0232.compute(49) + 40, ClaudeTask0232.compute(50));
    }

    @Test
    void isNonDecreasingAcrossTheRange() {
        int previous = ClaudeTask0232.compute(0);
        for (int value = 1; value <= 500; value++) {
            int current = ClaudeTask0232.compute(value);
            assertTrue(current >= previous, "premium decreased at value " + value);
            previous = current;
        }
    }
}
