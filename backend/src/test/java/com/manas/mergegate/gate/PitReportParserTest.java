package com.manas.mergegate.gate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link PitReportParser} against a hand-written, PIT-shaped {@code mutations.xml} fixture
 * rather than a real PIT run, so this test is fast and does not depend on mutation testing
 * actually being executed. The real PIT output format is exercised for real by
 * {@code scripts/run_pit.sh} and the numbers in the README's Measured results section.
 */
class PitReportParserTest {

    @Test
    void parsesStatusClassLineAndMutator(@TempDir Path tempDir) throws IOException {
        String xml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <mutations>
                  <mutation detected='true' status='KILLED'>
                    <mutatedClass>com.example.Foo</mutatedClass>
                    <mutatedMethod>compute</mutatedMethod>
                    <lineNumber>10</lineNumber>
                    <mutator>org.pitest.mutationtest.engine.gregor.mutators.MathMutator</mutator>
                  </mutation>
                  <mutation detected='false' status='SURVIVED'>
                    <mutatedClass>com.example.Bar</mutatedClass>
                    <mutatedMethod>compute</mutatedMethod>
                    <lineNumber>22</lineNumber>
                    <mutator>org.pitest.mutationtest.engine.gregor.mutators.ReturnValsMutator</mutator>
                  </mutation>
                </mutations>
                """;
        Path file = tempDir.resolve("mutations.xml");
        Files.writeString(file, xml);

        List<Mutation> mutations = new PitReportParser().parse(file.toFile());

        assertThat(mutations).hasSize(2);
        assertThat(mutations.get(0).mutatedClass()).isEqualTo("com.example.Foo");
        assertThat(mutations.get(0).lineNumber()).isEqualTo(10);
        assertThat(mutations.get(0).isKilled()).isTrue();
        assertThat(mutations.get(1).mutatedClass()).isEqualTo("com.example.Bar");
        assertThat(mutations.get(1).isSurvived()).isTrue();
        assertThat(mutations.get(1).isCovered()).isTrue();
    }

    @Test
    void emptyMutationsFileParsesToEmptyList(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("mutations.xml");
        Files.writeString(file, "<mutations></mutations>");
        assertThat(new PitReportParser().parse(file.toFile())).isEmpty();
    }
}
