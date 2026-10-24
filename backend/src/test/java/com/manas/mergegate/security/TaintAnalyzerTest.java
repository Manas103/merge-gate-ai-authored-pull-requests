package com.manas.mergegate.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaintAnalyzerTest {

    private final TaintAnalyzer analyzer = new TaintAnalyzer();

    @Test
    void flagsARequestParameterConcatenatedIntoAnExecuteQueryCall() {
        String source = """
                public List<Row> lookup(@RequestParam String accountRef) {
                    Statement stmt = connection.createStatement();
                    return stmt.executeQuery("SELECT * FROM accounts WHERE account_id = '" + accountRef + "'");
                }
                """;
        assertTrue(analyzer.flags(source));
    }

    @Test
    void flagsARequestParameterConcatenatedIntoRuntimeExec() {
        String source = """
                public String ping(@RequestParam String host) throws IOException {
                    Process p = Runtime.getRuntime().exec("ping -c 1 " + host);
                    return readOutput(p);
                }
                """;
        assertTrue(analyzer.flags(source));
    }

    @Test
    void doesNotFlagAPreparedStatementWithAPlaceholder() {
        String source = """
                public List<Row> lookup(@RequestParam String accountRef) {
                    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM accounts WHERE account_id = ?");
                    stmt.setString(1, accountRef);
                    return stmt.executeQuery();
                }
                """;
        assertFalse(analyzer.flags(source));
    }

    @Test
    void doesNotFlagAProcessBuilderWithFixedArguments() {
        String source = """
                public String ping(@RequestParam String host) throws IOException {
                    ProcessBuilder pb = new ProcessBuilder("ping", "-c", "1", "localhost");
                    Process proc = pb.start();
                    return readOutput(proc);
                }
                """;
        assertFalse(analyzer.flags(source));
    }
}
