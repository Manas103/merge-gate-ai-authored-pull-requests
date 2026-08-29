package com.manas.mergegate.corpus.pr0243;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ClaudeTask0243Test {

    @Test
    @DisplayName("free grace period covers the first half hour")
    void freeGracePeriod() {
        assertEquals(0, ClaudeTask0243.compute(0));
        assertEquals(0, ClaudeTask0243.compute(1));
        assertEquals(0, ClaudeTask0243.compute(15));
        assertEquals(0, ClaudeTask0243.compute(30));
    }

    @Test
    @DisplayName("short stays up to two hours cost 250 cents")
    void shortStay() {
        assertEquals(250, ClaudeTask0243.compute(31));
        assertEquals(250, ClaudeTask0243.compute(75));
        assertEquals(250, ClaudeTask0243.compute(120));
    }

    @Test
    @DisplayName("day stays up to eight hours cost 900 cents")
    void dayStay() {
        assertEquals(900, ClaudeTask0243.compute(121));
        assertEquals(900, ClaudeTask0243.compute(300));
        assertEquals(900, ClaudeTask0243.compute(480));
    }

    @Test
    @DisplayName("anything beyond eight hours costs the flat maximum")
    void overnightStay() {
        assertEquals(1800, ClaudeTask0243.compute(481));
        assertEquals(1800, ClaudeTask0243.compute(1440));
        assertEquals(1800, ClaudeTask0243.compute(Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -30, -720, Integer.MIN_VALUE})
    @DisplayName("negative minutes fall into the free branch")
    void negativeMinutes(int minutesParked) {
        assertEquals(0, ClaudeTask0243.compute(minutesParked));
    }

    @ParameterizedTest
    @CsvSource({
        "30, 0",
        "31, 250",
        "120, 250",
        "121, 900",
        "480, 900",
        "481, 1800"
    })
    @DisplayName("rate changes exactly at the documented boundaries")
    void boundaries(int minutesParked, int expectedCents) {
        assertEquals(expectedCents, ClaudeTask0243.compute(minutesParked));
    }

    @Test
    @DisplayName("rate never decreases as parking time grows")
    void rateIsMonotonic() {
        int previous = ClaudeTask0243.compute(0);
        for (int minutesParked = 1; minutesParked <= 600; minutesParked++) {
            int current = ClaudeTask0243.compute(minutesParked);
            org.junit.jupiter.api.Assertions.assertTrue(
                current >= previous,
                "rate dropped at " + minutesParked);
            previous = current;
        }
    }
}
