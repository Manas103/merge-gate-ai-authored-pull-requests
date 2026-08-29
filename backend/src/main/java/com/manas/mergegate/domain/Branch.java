package com.manas.mergegate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Branch {
    @Id
    private String branchId;
    private String prId;
    private String className;
    private String fqcn;
    private int lineNumber;
    private String description;
    private String constructionQuality;
    private boolean covered;
    private boolean flagged;
    private int mutantCount;
    private int killedCount;
    private int survivedCount;
    private int noCoverageCount;
    private String reason;

    protected Branch() { }

    public Branch(String branchId, String prId, String className, String fqcn, int lineNumber,
                  String description, String constructionQuality, boolean covered, boolean flagged,
                  int mutantCount, int killedCount, int survivedCount, int noCoverageCount, String reason) {
        this.branchId = branchId;
        this.prId = prId;
        this.className = className;
        this.fqcn = fqcn;
        this.lineNumber = lineNumber;
        this.description = description;
        this.constructionQuality = constructionQuality;
        this.covered = covered;
        this.flagged = flagged;
        this.mutantCount = mutantCount;
        this.killedCount = killedCount;
        this.survivedCount = survivedCount;
        this.noCoverageCount = noCoverageCount;
        this.reason = reason;
    }

    public String getBranchId() { return branchId; }
    public String getPrId() { return prId; }
    public String getClassName() { return className; }
    public String getFqcn() { return fqcn; }
    public int getLineNumber() { return lineNumber; }
    public String getDescription() { return description; }
    public String getConstructionQuality() { return constructionQuality; }
    public boolean isCovered() { return covered; }
    public boolean isFlagged() { return flagged; }
    public int getMutantCount() { return mutantCount; }
    public int getKilledCount() { return killedCount; }
    public int getSurvivedCount() { return survivedCount; }
    public int getNoCoverageCount() { return noCoverageCount; }
    public String getReason() { return reason; }
}
