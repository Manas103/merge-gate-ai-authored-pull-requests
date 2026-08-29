package com.manas.mergegate.corpus.pr0242;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ClaudeTask0242Test {

    @Test
    @DisplayName("no stops remaining means no surcharge")
    void returnsZeroWhenNoStopsRemain() {
        assertEquals(0, ClaudeTask0242.compute(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -7, Integer.MIN_VALUE})
    @DisplayName("negative stop counts are treated as no stops")
    void returnsZeroForNegativeStops(int stopsRemaining) {
        assertEquals(0, ClaudeTask0242.compute(stopsRemaining));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("short routes pay the flat base surcharge")
    void returnsFlatBaseForShortRoutes(int stopsRemaining) {
        assertEquals(150, ClaudeTask0242.compute(stopsRemaining));
    }

    @ParameterizedTest
    @CsvSource({
        "4, 315",
        "5, 355",
        "6, 395",
        "7, 435",
        "8, 475"
    })
    @DisplayName("mid routes add 40 cents per stop past the third")
    void addsPerStopChargeForMidRoutes(int stopsRemaining, int expected) {
        assertEquals(expected, ClaudeTask0242.compute(stopsRemaining));
    }

    @ParameterizedTest
    @CsvSource({
        "9, 500",
        "12, 575",
        "20, 775"
    })
    @DisplayName("long routes add 25 cents per stop past the eighth")
    void addsDiscountedPerStopChargeForLongRoutes(int stopsRemaining, int expected) {
        assertEquals(expected, ClaudeTask0242.compute(stopsRemaining));
    }

    @Test
    @DisplayName("surcharge is non decreasing across the boundaries")
    void surchargeIsNonDecreasingAcrossBoundaries() {
        int previous = ClaudeTask0242.compute(0);
        for (int stopsRemaining = 1; stopsRemaining <= 25; stopsRemaining++) {
            int current = ClaudeTask0242.compute(stopsRemaining);
            org.junit.jupiter.api.Assertions.assertTrue(
                current >= previous,
                "surcharge dropped at stopsRemaining=" + stopsRemaining);
            previous = current;
        }
    }
}
