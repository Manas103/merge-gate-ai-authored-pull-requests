package com.manas.mergegate.gate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The mutation-score-floor half of the release gate. Reuses GateEngine's own
 * per-branch BranchGateResult output (built from a real PitReportParser run)
 * and rolls each PR unit's branches up into one overall mutation score,
 * blocking any PR whose score falls below 70%. This is a stricter,
 * whole-suite version of GateEngine's per-branch "covered yet surviving"
 * rule: a PR can have zero individually-flagged branches and still fail this
 * rule if enough of its mutants survive across the whole unit, and a PR
 * whose suite passes while killing no mutants at all (the zero-kill case)
 * always fails it.
 */
public final class MutationScoreGate {

    public List<PrMutationScore> scoreAll(List<BranchGateResult> allResults) {
        Map<String, List<BranchGateResult>> byPr = new LinkedHashMap<>();
        for (BranchGateResult r : allResults) {
            byPr.computeIfAbsent(r.prId(), k -> new ArrayList<>()).add(r);
        }
        List<PrMutationScore> out = new ArrayList<>();
        for (Map.Entry<String, List<BranchGateResult>> e : byPr.entrySet()) {
            out.add(PrMutationScore.of(e.getKey(), e.getValue()));
        }
        return out;
    }

    public boolean isBlockedByMutationScoreFloor(String prId, List<PrMutationScore> allScores) {
        return allScores.stream()
                .filter(s -> s.prId().equals(prId))
                .anyMatch(PrMutationScore::belowFloor);
    }
}
