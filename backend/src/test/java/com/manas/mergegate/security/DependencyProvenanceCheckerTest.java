package com.manas.mergegate.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DependencyProvenanceCheckerTest {

    private final DependencyProvenanceChecker checker = new DependencyProvenanceChecker();

    @Test
    void doesNotFlagARealRegistryPackage() {
        assertFalse(checker.flags("guava"));
    }

    @Test
    void flagsAPlausibleSoundingPackageNoRegistryServes() {
        assertTrue(checker.flags("guava-extra-collections"));
    }
}
