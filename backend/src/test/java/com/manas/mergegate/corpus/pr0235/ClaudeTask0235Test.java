package com.manas.mergegate.corpus.pr0235;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ClaudeTask0235Test {

    @Test
    @DisplayName("no active sensors yields the unoccupied score")
    void returnsZeroWhenNoSensorsAreActive() {
        assertEquals(0, ClaudeTask0235.compute(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -7, Integer.MIN_VALUE})
    @DisplayName("negative sensor counts fall back to the unoccupied score")
    void returnsZeroForNegativeCounts(int value) {
        assertEquals(0, ClaudeTask0235.compute(value));
    }

    @ParameterizedTest
    @CsvSource({"1, 30", "2, 35", "3, 40"})
    @DisplayName("low occupancy scales from the eco baseline")
    void scoresLowOccupancy(int value, int expected) {
        assertEquals(expected, ClaudeTask0235.compute(value));
    }

    @ParameterizedTest
    @CsvSource({"4, 68", "7, 74", "10, 80"})
    @DisplayName("moderate occupancy scales from the comfort baseline")
    void scoresModerateOccupancy(int value, int expected) {
        assertEquals(expected, ClaudeTask0235.compute(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 50, Integer.MAX_VALUE})
    @DisplayName("high occupancy saturates at the maximum score")
    void saturatesForHighOccupancy(int value) {
        assertEquals(95, ClaudeTask0235.compute(value));
    }

    @Test
    @DisplayName("scores never decrease as occupancy rises")
    void isMonotonicAcrossBranchBoundaries() {
        int previous = ClaudeTask0235.compute(-5);
        for (int value = -4; value <= 20; value++) {
            int current = ClaudeTask0235.compute(value);
            org.junit.jupiter.api.Assertions.assertTrue(
                    current >= previous,
                    "score dropped at value " + value);
            previous = current;
        }
    }
}
