package com.manas.mergegate.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the right rule checker for a {@link SecurityCase} depending on its {@code ruleType}, and
 * is also the real per-PR decision a merge gate would act on: {@link #flagsSource} is true if any
 * of the three rules fires on a given file's source text, independent of which rule it was.
 */
public final class SecurityGate {

    private final TaintAnalyzer taintAnalyzer = new TaintAnalyzer();
    private final AuthorizationDiffChecker authorizationDiffChecker = new AuthorizationDiffChecker();
    private final DependencyProvenanceChecker dependencyProvenanceChecker = new DependencyProvenanceChecker();

    public boolean flagsSource(String source) {
        return taintAnalyzer.flags(source) || authorizationDiffChecker.flags(source);
    }

    public boolean flagsPackageName(String packageName) {
        return dependencyProvenanceChecker.flags(packageName);
    }

    public SecurityCaseResult evaluate(SecurityCase c) {
        boolean actual = switch (c.ruleType()) {
            case "taint" -> taintAnalyzer.flags(c.snippet());
            case "authz" -> authorizationDiffChecker.flags(c.snippet());
            case "provenance" -> dependencyProvenanceChecker.flags(c.snippet());
            default -> throw new IllegalArgumentException("unknown ruleType " + c.ruleType());
        };
        return SecurityCaseResult.of(c, actual);
    }

    public List<SecurityCaseResult> evaluateAll(List<SecurityCase> cases) {
        List<SecurityCaseResult> results = new ArrayList<>();
        for (SecurityCase c : cases) {
            results.add(evaluate(c));
        }
        return results;
    }
}
