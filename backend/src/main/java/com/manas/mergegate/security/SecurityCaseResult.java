package com.manas.mergegate.security;

public record SecurityCaseResult(String id, String ruleType, String label, boolean expectedFlag,
                                  boolean actualFlag, boolean falsePositive, boolean missed) {

    public static SecurityCaseResult of(SecurityCase c, boolean actualFlag) {
        boolean falsePositive = actualFlag && !c.expectedFlag();
        boolean missed = c.expectedFlag() && !actualFlag;
        return new SecurityCaseResult(c.id(), c.ruleType(), c.label(), c.expectedFlag(), actualFlag, falsePositive, missed);
    }
}
