package com.manas.mergegate.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Traces a request parameter into a SQL or shell sink. Deliberately scoped: a single-hop,
 * same-line, intraprocedural tracer, not a general interprocedural dataflow analysis. It finds
 * parameters declared with {@code @RequestParam}/{@code @PathVariable}, then flags any line that
 * both calls a known sink ({@code executeQuery}/{@code executeUpdate}/{@code execute} on a
 * {@code Statement}, or {@code Runtime.exec}/{@code new ProcessBuilder}) and concatenates one of
 * those tainted parameters into the sink's string argument with {@code +}. A
 * {@code PreparedStatement} built from a literal with {@code ?} placeholders, or a
 * {@code ProcessBuilder} given fixed string-literal arguments, is not flagged, because the
 * tainted value never reaches the sink as part of the executed string/command.
 */
public final class TaintAnalyzer {

    private static final Pattern PARAM_PATTERN =
            Pattern.compile("@(?:RequestParam|PathVariable)(?:\\([^)]*\\))?\\s+\\w+\\s+(\\w+)");
    private static final Pattern SQL_SINK = Pattern.compile("\\.(executeQuery|executeUpdate|execute)\\s*\\(");
    private static final Pattern SHELL_SINK = Pattern.compile("Runtime\\.getRuntime\\(\\)\\.exec\\(|new\\s+ProcessBuilder\\(");

    public record Finding(String sinkType, int lineNumber, String taintedParam) {
    }

    public List<Finding> analyze(String source) {
        List<String> taintedParams = new ArrayList<>();
        Matcher paramMatcher = PARAM_PATTERN.matcher(source);
        while (paramMatcher.find()) {
            taintedParams.add(paramMatcher.group(1));
        }

        List<Finding> findings = new ArrayList<>();
        String[] lines = source.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String concatenatedParam = concatenatesAny(line, taintedParams);
            if (concatenatedParam == null) {
                continue;
            }
            if (SQL_SINK.matcher(line).find()) {
                findings.add(new Finding("sql", i + 1, concatenatedParam));
            } else if (SHELL_SINK.matcher(line).find()) {
                findings.add(new Finding("shell", i + 1, concatenatedParam));
            }
        }
        return findings;
    }

    public boolean flags(String source) {
        return !analyze(source).isEmpty();
    }

    private String concatenatesAny(String line, List<String> taintedParams) {
        for (String param : taintedParams) {
            if (line.contains("+ " + param) || line.contains("+" + param + ")") || line.contains("+ " + param + ")")) {
                return param;
            }
        }
        return null;
    }
}
