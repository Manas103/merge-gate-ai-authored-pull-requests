package com.manas.mergegate.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Proves the Honest framing claim that wiring the security gate into the PR-level decision
 * ({@code mutationFlagged || securityFlagged}) does not change the 60-PR holdout's outcome: it
 * reads every holdout PR's real committed implementation source and runs SecurityGate.flagsSource
 * over it directly, rather than assuming none of the 245 PR units contain a SQL/shell/endpoint/
 * dependency surface.
 */
class SecurityHoldoutIntegrationTest {

    @Test
    void noneOfTheSixtyHoldoutPrUnitsTripAnySecurityRule() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode holdout = mapper.readTree(new File("src/main/resources/corpus/holdout_manifest.json"));
        assertEquals(60, holdout.size());

        SecurityGate gate = new SecurityGate();
        List<String> flagged = new ArrayList<>();

        for (JsonNode entry : holdout) {
            String prId = entry.get("prId").asText();
            File dir = new File("src/main/java/com/manas/mergegate/corpus/" + prId);
            File[] javaFiles = dir.listFiles((d, name) -> name.endsWith(".java"));
            assertTrue(javaFiles != null && javaFiles.length > 0, "no implementation file for " + prId);
            for (File javaFile : javaFiles) {
                String source = Files.readString(javaFile.toPath());
                if (gate.flagsSource(source)) {
                    flagged.add(prId + "/" + javaFile.getName());
                }
            }
        }

        assertTrue(flagged.isEmpty(), "security gate unexpectedly flagged holdout PR source: " + flagged);
    }
}
