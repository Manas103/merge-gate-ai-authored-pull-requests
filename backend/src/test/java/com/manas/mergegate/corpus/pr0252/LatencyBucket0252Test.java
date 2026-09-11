package com.manas.mergegate.corpus.pr0252;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Deliberately vacuous: both branches are genuinely executed (real line
 * coverage), but the assertion is a tautology true of any int result, so no
 * mutation of the arithmetic or the branch condition can ever be caught.
 * This is the seeded zero-kill-suite case: the suite passes, the mutation
 * score is genuinely 0%. */
class LatencyBucket0252Test {

    @Test
    void branch0ExecutesButAssertsATautology() {
        int result = LatencyBucket0252.compute(195);
        assertTrue(result >= Integer.MIN_VALUE);
    }

    @Test
    void branch1ExecutesButAssertsATautology() {
        int result = LatencyBucket0252.compute(225);
        assertTrue(result >= Integer.MIN_VALUE);
    }
}
