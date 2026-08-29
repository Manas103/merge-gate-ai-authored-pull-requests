package com.manas.mergegate.gate;

/**
 * One mutant result as reported by PIT in mutations.xml. status is PIT's own
 * vocabulary: KILLED, SURVIVED, NO_COVERAGE, TIMED_OUT, MEMORY_ERROR,
 * RUN_ERROR, NON_VIABLE. SURVIVED means a test executed the mutated line
 * (it is covered) but no test failed, so the mutation went undetected; that
 * is exactly "line-covered yet mutation-survives".
 */
public record Mutation(String mutatedClass, int lineNumber, String status, String mutator) {

    public boolean isCovered() {
        return !"NO_COVERAGE".equals(status);
    }

    public boolean isSurvived() {
        return "SURVIVED".equals(status);
    }

    public boolean isKilled() {
        return "KILLED".equals(status);
    }
}
