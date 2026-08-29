package com.manas.mergegate.corpus.pr0233;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ClaudeTask0233Test {

    @Test
    @DisplayName("negative order counts earn no bonus")
    void negativeOrdersEarnNoBonus() {
        assertEquals(0, ClaudeTask0233.compute(-1));
        assertEquals(0, ClaudeTask0233.compute(-250));
        assertEquals(0, ClaudeTask0233.compute(Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("zero orders earns no bonus")
    void zeroOrdersEarnsNoBonus() {
        assertEquals(0, ClaudeTask0233.compute(0));
    }

    @Test
    @DisplayName("entry tier awards 10 points per order")
    void entryTierAwardsTenPointsPerOrder() {
        assertEquals(10, ClaudeTask0233.compute(1));
        assertEquals(20, ClaudeTask0233.compute(2));
        assertEquals(40, ClaudeTask0233.compute(4));
    }

    @Test
    @DisplayName("middle tier builds on the entry tier total")
    void middleTierBuildsOnEntryTierTotal() {
        assertEquals(50, ClaudeTask0233.compute(5));
        assertEquals(65, ClaudeTask0233.compute(6));
        assertEquals(260, ClaudeTask0233.compute(19));
    }

    @Test
    @DisplayName("top tier builds on the middle tier total")
    void topTierBuildsOnMiddleTierTotal() {
        assertEquals(275, ClaudeTask0233.compute(20));
        assertEquals(300, ClaudeTask0233.compute(21));
        assertEquals(775, ClaudeTask0233.compute(40));
    }

    @ParameterizedTest
    @DisplayName("tier boundaries stay continuous")
    @CsvSource({"0, 0", "4, 40", "5, 50", "19, 260", "20, 275"})
    void tierBoundariesStayContinuous(int orders, int expected) {
        assertEquals(expected, ClaudeTask0233.compute(orders));
    }

    @Test
    @DisplayName("bonus never decreases as order count grows")
    void bonusNeverDecreasesAsOrderCountGrows() {
        int previous = ClaudeTask0233.compute(-5);
        for (int orders = -4; orders <= 60; orders++) {
            int current = ClaudeTask0233.compute(orders);
            org.junit.jupiter.api.Assertions.assertTrue(
                    current >= previous,
                    "bonus decreased at orders=" + orders);
            previous = current;
        }
    }
}
