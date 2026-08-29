package com.manas.mergegate.corpus.pr0231;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ClaudeTask0231Test {

    @Test
    @DisplayName("no fee within the grace period")
    void gracePeriodIsFree() {
        assertEquals(0, ClaudeTask0231.compute(0));
        assertEquals(0, ClaudeTask0231.compute(1));
        assertEquals(0, ClaudeTask0231.compute(15));
    }

    @Test
    @DisplayName("first tier applies just past the grace period")
    void firstTier() {
        assertEquals(250, ClaudeTask0231.compute(16));
        assertEquals(250, ClaudeTask0231.compute(45));
        assertEquals(250, ClaudeTask0231.compute(60));
    }

    @Test
    @DisplayName("second tier applies past one hour")
    void secondTier() {
        assertEquals(1000, ClaudeTask0231.compute(61));
        assertEquals(1000, ClaudeTask0231.compute(120));
        assertEquals(1000, ClaudeTask0231.compute(240));
    }

    @Test
    @DisplayName("top tier applies past four hours")
    void topTier() {
        assertEquals(2500, ClaudeTask0231.compute(241));
        assertEquals(2500, ClaudeTask0231.compute(1440));
        assertEquals(2500, ClaudeTask0231.compute(Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -30, Integer.MIN_VALUE})
    @DisplayName("early arrivals are never charged")
    void negativeMinutesAreFree(int minutesLate) {
        assertEquals(0, ClaudeTask0231.compute(minutesLate));
    }

    @ParameterizedTest
    @CsvSource({
        "15, 0",
        "16, 250",
        "60, 250",
        "61, 1000",
        "240, 1000",
        "241, 2500"
    })
    @DisplayName("fees change exactly at the tier boundaries")
    void boundaries(int minutesLate, int expectedCents) {
        assertEquals(expectedCents, ClaudeTask0231.compute(minutesLate));
    }

    @Test
    @DisplayName("the fee never decreases as lateness grows")
    void feeIsMonotonic() {
        int previous = ClaudeTask0231.compute(0);
        for (int minutesLate = 1; minutesLate <= 500; minutesLate++) {
            int current = ClaudeTask0231.compute(minutesLate);
            org.junit.jupiter.api.Assertions.assertTrue(
                current >= previous,
                "fee dropped at " + minutesLate);
            previous = current;
        }
    }
}
