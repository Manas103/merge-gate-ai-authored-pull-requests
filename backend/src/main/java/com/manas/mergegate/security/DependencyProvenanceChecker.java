package com.manas.mergegate.security;

import java.util.Set;

/**
 * Rejects a package name no registry serves. The registry here is a fixed, committed allow-list
 * of real package names ({@link #KNOWN_REGISTRY}), not a live call to Maven Central or npm; this
 * is a disclosed simplification of what a real provenance check would query. A name not on the
 * list is flagged as hallucinated, the failure mode where an AI assistant invents a
 * plausible-sounding dependency that no registry actually serves.
 */
public final class DependencyProvenanceChecker {

    public static final Set<String> KNOWN_REGISTRY = Set.of(
            "spring-boot-starter-web", "spring-boot-starter-data-jpa", "jackson-databind",
            "lombok", "junit-jupiter", "mockito-core", "guava", "commons-lang3",
            "slf4j-api", "h2");

    public boolean flags(String packageName) {
        return !KNOWN_REGISTRY.contains(packageName.trim());
    }
}
