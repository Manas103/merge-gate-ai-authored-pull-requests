package com.manas.mergegate.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorizationDiffCheckerTest {

    private final AuthorizationDiffChecker checker = new AuthorizationDiffChecker();

    @Test
    void flagsANewEndpointWithNoAuthorizationAnnotation() {
        String source = """
                @RestController
                public class WidgetController {
                    @GetMapping("/api/widgets")
                    public ResponseEntity<Widget> get(@PathVariable String id) {
                        return ResponseEntity.ok(service.find(id));
                    }
                }
                """;
        assertTrue(checker.flags(source));
    }

    @Test
    void doesNotFlagAnEndpointWithAMethodLevelPreAuthorize() {
        String source = """
                @RestController
                public class WidgetController {
                    @PreAuthorize("hasRole('ADMIN')")
                    @GetMapping("/api/widgets")
                    public ResponseEntity<Widget> get(@PathVariable String id) {
                        return ResponseEntity.ok(service.find(id));
                    }
                }
                """;
        assertFalse(checker.flags(source));
    }

    @Test
    void doesNotFlagAnEndpointWithAClassLevelSecured() {
        String source = """
                @Secured("ROLE_ADMIN")
                @RestController
                public class WidgetController {
                    @GetMapping("/api/widgets")
                    public ResponseEntity<Widget> get(@PathVariable String id) {
                        return ResponseEntity.ok(service.find(id));
                    }
                }
                """;
        assertFalse(checker.flags(source));
    }
}
