package com.manas.mergegate.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Flags a new endpoint (a method annotated with a Spring mapping annotation) that carries no
 * authorization check. "Carries" means an {@code @PreAuthorize} or {@code @Secured} annotation
 * within the 3 lines directly above the mapping annotation, or at class level above the class
 * declaration; this is a textual proximity check over a diff's added lines, not a call-graph
 * analysis of whether some other layer enforces authorization elsewhere.
 */
public final class AuthorizationDiffChecker {

    private static final Pattern MAPPING = Pattern.compile("@(?:Get|Post|Put|Delete|Request)Mapping\\(");
    private static final Pattern AUTH_ANNOTATION = Pattern.compile("@(?:PreAuthorize|Secured|RolesAllowed)\\(");
    private static final int LOOKBACK_LINES = 3;

    public record Finding(int lineNumber, String mappingLine) {
    }

    public List<Finding> analyze(String source) {
        String[] lines = source.split("\n");
        boolean classLevelAuth = hasClassLevelAuth(lines);

        List<Finding> findings = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (!MAPPING.matcher(lines[i]).find()) {
                continue;
            }
            if (classLevelAuth || hasAuthWithinLookback(lines, i)) {
                continue;
            }
            findings.add(new Finding(i + 1, lines[i].trim()));
        }
        return findings;
    }

    public boolean flags(String source) {
        return !analyze(source).isEmpty();
    }

    private boolean hasClassLevelAuth(String[] lines) {
        for (String line : lines) {
            if (line.contains("class ")) {
                return false;
            }
            if (AUTH_ANNOTATION.matcher(line).find()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAuthWithinLookback(String[] lines, int mappingIndex) {
        for (int j = Math.max(0, mappingIndex - LOOKBACK_LINES); j < mappingIndex; j++) {
            if (AUTH_ANNOTATION.matcher(lines[j]).find()) {
                return true;
            }
        }
        return false;
    }
}
