package com.manas.mergegate.gate;

/** The gate's decision for one branch: real output of intersecting PIT's
 * per-line mutation results with the branch registry. flagged is true only
 * when the line is covered (some mutant reached it) and at least one mutant
 * at that line survived, meaning the covering test never actually checked
 * that branch's effect. */
public record BranchGateResult(
        String prId,
        String branchId,
        String fqcn,
        int lineNumber,
        boolean covered,
        boolean flagged,
        int mutantCount,
        int killedCount,
        int survivedCount,
        int noCoverageCount,
        String reason) {

    public static BranchGateResult of(BranchDef def, java.util.List<Mutation> atLine) {
        int mutantCount = atLine.size();
        long killed = atLine.stream().filter(Mutation::isKilled).count();
        long survived = atLine.stream().filter(Mutation::isSurvived).count();
        long noCoverage = atLine.stream().filter(m -> !m.isCovered()).count();
        boolean covered = mutantCount > 0 && noCoverage < mutantCount;
        boolean flagged = covered && survived > 0;
        String reason;
        if (mutantCount == 0) {
            reason = "no mutants generated at this line";
        } else if (!covered) {
            reason = "not covered by any test (NO_COVERAGE)";
        } else if (flagged) {
            reason = survived + " of " + mutantCount + " mutant(s) at this line survived despite coverage";
        } else {
            reason = "covered and all " + killed + " mutant(s) at this line were killed";
        }
        return new BranchGateResult(def.prId(), def.branchId(), def.fqcn(), def.lineNumber(),
                covered, flagged, mutantCount, (int) killed, (int) survived, (int) noCoverage, reason);
    }
}
