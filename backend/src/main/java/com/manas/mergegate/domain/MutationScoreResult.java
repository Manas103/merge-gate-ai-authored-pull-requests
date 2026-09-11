package com.manas.mergegate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/** Persisted PrMutationScore row for one PR unit evaluated against the
 * mutation-score-floor rule (70%). Currently loaded from the 9 seeded
 * zero-kill PR units; see seed.CorpusDataLoader and
 * gate.MutationScoreGateMain. */
@Entity
public class MutationScoreResult {
    @Id
    private String prId;
    private int killedCount;
    private int survivedCount;
    private int noCoverageCount;
    private boolean hasScoreableMutants;
    private double score;
    private boolean belowFloor;
    private boolean zeroKill;
    private String reason;

    protected MutationScoreResult() { }

    public MutationScoreResult(String prId, int killedCount, int survivedCount, int noCoverageCount,
                                boolean hasScoreableMutants, double score, boolean belowFloor,
                                boolean zeroKill, String reason) {
        this.prId = prId;
        this.killedCount = killedCount;
        this.survivedCount = survivedCount;
        this.noCoverageCount = noCoverageCount;
        this.hasScoreableMutants = hasScoreableMutants;
        this.score = score;
        this.belowFloor = belowFloor;
        this.zeroKill = zeroKill;
        this.reason = reason;
    }

    public String getPrId() { return prId; }
    public int getKilledCount() { return killedCount; }
    public int getSurvivedCount() { return survivedCount; }
    public int getNoCoverageCount() { return noCoverageCount; }
    public boolean isHasScoreableMutants() { return hasScoreableMutants; }
    public double getScore() { return score; }
    public boolean isBelowFloor() { return belowFloor; }
    public boolean isZeroKill() { return zeroKill; }
    public String getReason() { return reason; }
}
