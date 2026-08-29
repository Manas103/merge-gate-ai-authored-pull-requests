package com.manas.mergegate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class HoldoutResult {
    @Id
    private String prId;
    private String source;
    private String labelMethod;
    private boolean expectedBlock;
    private boolean actualBlock;
    private boolean falseBlock;
    private boolean missedBlock;

    protected HoldoutResult() { }

    public HoldoutResult(String prId, String source, String labelMethod, boolean expectedBlock,
                          boolean actualBlock, boolean falseBlock, boolean missedBlock) {
        this.prId = prId;
        this.source = source;
        this.labelMethod = labelMethod;
        this.expectedBlock = expectedBlock;
        this.actualBlock = actualBlock;
        this.falseBlock = falseBlock;
        this.missedBlock = missedBlock;
    }

    public String getPrId() { return prId; }
    public String getSource() { return source; }
    public String getLabelMethod() { return labelMethod; }
    public boolean isExpectedBlock() { return expectedBlock; }
    public boolean isActualBlock() { return actualBlock; }
    public boolean isFalseBlock() { return falseBlock; }
    public boolean isMissedBlock() { return missedBlock; }
}
