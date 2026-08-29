package com.manas.mergegate.gate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The real merge gate mechanism. Given PIT's raw mutation results and the
 * corpus's branch registry, decides per branch whether it is "covered yet
 * mutation survives": the PR's own tests execute the line but no assertion
 * actually distinguishes correct behavior from a broken mutation of it.
 * A PR is blocked if any of its tracked branches is flagged.
 */
public final class GateEngine {

    public List<BranchGateResult> evaluate(List<BranchDef> branches, List<Mutation> mutations) {
        Map<String, List<Mutation>> byClass = mutations.stream()
                .collect(Collectors.groupingBy(Mutation::mutatedClass));
        List<BranchGateResult> results = new ArrayList<>();
        for (BranchDef def : branches) {
            List<Mutation> classMutations = byClass.getOrDefault(def.fqcn(), List.of());
            List<Mutation> atLine = classMutations.stream()
                    .filter(m -> m.lineNumber() == def.lineNumber())
                    .toList();
            results.add(BranchGateResult.of(def, atLine));
        }
        return results;
    }

    /** True if any branch belonging to prId is flagged: the PR-level decision
     * a real merge gate would act on. */
    public boolean isPrBlocked(String prId, List<BranchGateResult> allResults) {
        return allResults.stream()
                .filter(r -> r.prId().equals(prId))
                .anyMatch(BranchGateResult::flagged);
    }
}
