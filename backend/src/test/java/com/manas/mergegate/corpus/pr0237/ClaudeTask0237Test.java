package com.manas.mergegate.corpus.pr0237;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ClaudeTask0237Test {

    @Test
    @DisplayName("tickets answered within the first hours score lowest urgency")
    void freshTicketsScoreTen() {
        assertEquals(10, ClaudeTask0237.compute(0));
        assertEquals(10, ClaudeTask0237.compute(1));
        assertEquals(10, ClaudeTask0237.compute(3));
    }

    @Test
    @DisplayName("tickets waiting less than a day score forty")
    void sameDayTicketsScoreForty() {
        assertEquals(40, ClaudeTask0237.compute(4));
        assertEquals(40, ClaudeTask0237.compute(12));
        assertEquals(40, ClaudeTask0237.compute(23));
    }

    @Test
    @DisplayName("tickets waiting one to three days score seventy")
    void multiDayTicketsScoreSeventy() {
        assertEquals(70, ClaudeTask0237.compute(24));
        assertEquals(70, ClaudeTask0237.compute(48));
        assertEquals(70, ClaudeTask0237.compute(71));
    }

    @Test
    @DisplayName("tickets waiting three days or more score maximum urgency")
    void staleTicketsScoreOneHundred() {
        assertEquals(100, ClaudeTask0237.compute(72));
        assertEquals(100, ClaudeTask0237.compute(240));
        assertEquals(100, ClaudeTask0237.compute(Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, Integer.MIN_VALUE})
    @DisplayName("negative hours fall into the lowest band")
    void negativeHoursScoreTen(int hours) {
        assertEquals(10, ClaudeTask0237.compute(hours));
    }

    @Test
    @DisplayName("score is non-decreasing as hours grow")
    void scoreIsMonotonic() {
        int previous = ClaudeTask0237.compute(0);
        for (int hours = 1; hours <= 200; hours++) {
            int current = ClaudeTask0237.compute(hours);
            assertEquals(true, current >= previous, "score dropped at hour " + hours);
            previous = current;
        }
    }
}
